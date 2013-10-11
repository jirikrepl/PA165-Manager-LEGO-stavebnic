package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Color;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * @author Pavol Bako
 */
public class BuildingKitDaoTest extends TestCase {

    private BuildingKitDao buildingKitDao;
    private BrickDao brickDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        buildingKitDao = new BuildingKitDaoImpl();
        buildingKitDao.setEntityManager(emf.createEntityManager());
        brickDao = new BrickDaoImpl();
        brickDao.setEntityManager(emf.createEntityManager());
    }

    public void tearDow() throws Exception {
        super.tearDown();
    }

    public void testCreateBuildingKit() {
        System.out.println("TEST CreateBuildingKit");

        try {
            buildingKitDao.create(null);
            fail("creating NULL building kit");
        } catch (IllegalArgumentException ex) {
        }

        List<Brick> list = storeBricks();
        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.create(kit);
        assertNotNull(kit.getId());
        assertEquals(kit.getName(), "name");
        assertEquals(kit.getDescription(), "description");
        assertEquals(kit.getPrice(), BigDecimal.ZERO);
        assertEquals(kit.getYearFrom(), 2005);
    }

    public void testRemoveBuildingKit() {
        System.out.println("TEST CreateBuildingKit");
        
        try{
        buildingKitDao.delete(null);
        fail("removing NULL building kit");
        } catch (IllegalArgumentException ex){
        }
        
        Brick brick1 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");

        brickDao.create(brick1);
        brickDao.create(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.create(kit);
        Long iD = kit.getId();
        buildingKitDao.delete(kit.getId());
        
        assertNull(buildingKitDao.findById(iD));
        
    }

    public void testUpdateBuildingKit() {
        System.out.println("TEST UpdateBuildingKit");

        try {
            buildingKitDao.update(null);
            fail("updating NULL building kit");
        } catch (IllegalArgumentException ex) {
        }

        Brick brick1 = TestUtils.createBrick("TestBrickA", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrickB", Color.BLACK, "Test");

        brickDao.create(brick1);
        brickDao.create(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);
        List<Brick> newList = new ArrayList<Brick>();
        newList.add(brick2);
        newList.add(brick1);
        

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.create(kit);
        
        //BuildingKit newKit = TestUtils.createBuildingKit("newName", "newDescription", BigDecimal.TEN, 2010, newList);
        kit.setName("newName");
        kit.setDescription("newDescription");
        kit.setPrice(BigDecimal.TEN);
        kit.setYearFrom(2004);
        kit.setBricks(newList);
        
        buildingKitDao.update(kit);
        
        
        /*Long kitID = kit.getId();*/
        //newKit.setId(kit.getId());
        //buildingKitDao.UpdateBuildingKit(newKit);
        assertNotNull(kit.getId());
        assertEquals(kit.getName(), "newName");
        assertEquals(kit.getDescription(), "newDescription");
        assertEquals(kit.getPrice(), BigDecimal.TEN);
        assertEquals(kit.getYearFrom(), 2010);
        assertEquals(kit.getBricks(), newList);

    }

    public void testFindAll() {
        System.out.println("TEST Find All");
        
        List<Brick>list = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 2004, list);
        buildingKitDao.create(kit);
        buildingKitDao.create(kit2);
        
        List<BuildingKit> kitList = buildingKitDao.findAll();
        
        assertNotNull(kitList);
        assertTrue(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));
    }


    public void testFindByPrice() {
        System.out.println("TEST Find By Price");
        
        List<Brick>list = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.TEN, 2005, list);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 2006, list);
        buildingKitDao.create(kit);
        buildingKitDao.create(kit2);
        
        List<BuildingKit> kitList = buildingKitDao.findByPrice(BigDecimal.ZERO);
        
        assertNotNull(kitList);
        assertFalse(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));

    }

    public void testFindByYearFrom() {
        System.out.println("TEST Find By Year From");
        
        List<Brick>list = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 20, list);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 14, list);
        buildingKitDao.create(kit);
        buildingKitDao.create(kit2);
        
        List<BuildingKit> kitList = buildingKitDao.findByYearFrom(15);
        
        assertNotNull(kitList);
        assertTrue(kitList.contains(kit));
        assertFalse(kitList.contains(kit2));
        assertEquals(kitList.size(), 1);

    }
    
    public List<Brick> storeBricks(){
        Brick brick1 = TestUtils.createBrick("TestBrickA", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrickB", Color.BLACK, "Test");

        brickDao.create(brick1);
        brickDao.create(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);
        return list;
    }
}

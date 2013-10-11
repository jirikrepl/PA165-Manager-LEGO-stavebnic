
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Color;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
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
            buildingKitDao.createBuildingKit(null);
            fail("creating NULL building kit");
        } catch (IllegalArgumentException ex) {
        }

        Brick brick1 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");

        brickDao.storeBrick(brick1);
        brickDao.storeBrick(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.createBuildingKit(kit);
        assertNotNull(kit.getId());
        assertEquals(kit.getName(), "name");
        assertEquals(kit.getDescription(), "description");
        assertEquals(kit.getPrice(), BigDecimal.ZERO);
        assertEquals(kit.getYearFrom(), 2005);
    }

    public void removeBuildingKitTest() {
        System.out.println("TEST CreateBuildingKit");
        
        try{
        buildingKitDao.RemoveBuildingKit(null);
        fail("removing NULL building kit");
        } catch (IllegalArgumentException ex){
        }
        
        Brick brick1 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrick", Color.BLACK, "Test");

        brickDao.storeBrick(brick1);
        brickDao.storeBrick(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.CreateBuildingKit(kit);
        buildingKitDao.RemoveBuildingKit(kit);
        
        assertNull(kit.getId());
        assertNull(kit.getName());
        assertNull(kit.getDescription());
        assertNull(kit.getPrice());
        assertNull(kit.getYearFrom());
        assertNull(kit.getBricks());
    }

    public void updateBuildingKitTest() {
        System.out.println("TEST UpdateBuildingKit");

        try {
            buildingKitDao.UpdateBuildingKit(null);
            fail("updating NULL building kit");
        } catch (IllegalArgumentException ex) {
        }

        Brick brick1 = TestUtils.createBrick("TestBrickA", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrickB", Color.BLACK, "Test");

        brickDao.storeBrick(brick1);
        brickDao.storeBrick(brick2);

        List<Brick> list = new ArrayList<Brick>();
        list.add(brick1);
        list.add(brick2);
        List<Brick> newList = new ArrayList<Brick>();
        newList.add(brick2);
        newList.add(brick1);
        

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, list);
        buildingKitDao.CreateBuildingKit(kit);
        
        //BuildingKit newKit = TestUtils.createBuildingKit("newName", "newDescription", BigDecimal.TEN, 2010, newList);
        kit.setName("newName");
        kit.setDescription("newDescription");
        kit.setPrice(BigDecimal.ZERO);
        kit.setYearFrom(2010);
        kit.setBricks(newList);
        
        buildingKitDao.UpdateBuildingKit(kit);
        
        
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

    public void findAllTest() {

    }

    public void findByPrice() {

    }

    public void findByYearFrom() {

    }
}

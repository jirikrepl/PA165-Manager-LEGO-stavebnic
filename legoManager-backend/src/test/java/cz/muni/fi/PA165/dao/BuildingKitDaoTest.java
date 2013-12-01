package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.api.service.Color;
import cz.muni.fi.PA165.entity.Category;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 * @author Pavol Bako
 */
public class BuildingKitDaoTest extends TestCase {

    private BuildingKitDao buildingKitDao;
    private BrickDao brickDao;
    private CategoryDao categoryDao;
    private EntityManager em;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        buildingKitDao = new BuildingKitDaoImpl();
        buildingKitDao.setEntityManager(em);
        
        brickDao = new BrickDaoImpl();
        brickDao.setEntityManager(em);

        categoryDao = new CategoryDaoImpl();
        categoryDao.setEntityManager(em);
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

        Map<Brick, Integer> map = storeBricks();
        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
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

        em.getTransaction().begin();
        brickDao.create(brick1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        brickDao.create(brick2);
        em.getTransaction().commit();

        Map<Brick, Integer> map = new HashMap<Brick, Integer>();
        map.put(brick1, 8);
        map.put(brick2, 1);

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        Long iD = kit.getId();
        em.getTransaction().begin();
        buildingKitDao.delete(kit.getId());
        em.getTransaction().commit();

        try {
            buildingKitDao.findById(iD);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        
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
        Brick brick3 = TestUtils.createBrick("TestBrickC", Color.BLACK, "Test");
        Brick brick4 = TestUtils.createBrick("TestBrickD", Color.BLACK, "Test");

        em.getTransaction().begin();
        brickDao.create(brick1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        brickDao.create(brick2);
        em.getTransaction().commit();
        em.getTransaction().begin();
        brickDao.create(brick3);
        em.getTransaction().commit();
        em.getTransaction().begin();
        brickDao.create(brick4);
        em.getTransaction().commit();
        
        Map<Brick, Integer> map = new HashMap<Brick, Integer>();
        map.put(brick1, 71);
        map.put(brick2, 6);
        Map<Brick, Integer> newMap = new HashMap<Brick, Integer>();
        newMap.put(brick3, 1);
        newMap.put(brick4, 2);
        

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        
        BuildingKit newKit = TestUtils.createBuildingKit("newName", "newDescription", BigDecimal.TEN, 2010, newMap);
        kit.setName("newName");
        kit.setDescription("newDescription");
        kit.setPrice(BigDecimal.TEN);
        kit.setYearFrom(2004);
        kit.setBricks(newMap);
        
        em.getTransaction().begin();
        buildingKitDao.update(kit);
        em.getTransaction().commit();
        
        assertNotNull(kit.getId());
        assertEquals(kit.getName(), "newName");
        assertEquals(kit.getDescription(), "newDescription");
        assertEquals(kit.getPrice(), BigDecimal.TEN);
        assertEquals(kit.getYearFrom(), 2004);
        assertTrue(kit.getBricks().containsKey(brick3));
        assertTrue(kit.getBricks().containsKey(brick4));
        assertEquals(kit.getBricks().size(), 2);

    }

    public void testFindAll() {
        System.out.println("TEST Find All");
        
        Map<Brick, Integer> map = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, map);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 2004, map);

        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        em.getTransaction().begin();
        buildingKitDao.create(kit2);
        em.getTransaction().commit();
        
        List<BuildingKit> kitList = buildingKitDao.findAll();
        
        assertNotNull(kitList);
        assertTrue(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));
    }


    public void testFindByPrice() {
        System.out.println("TEST Find By Price");
        
        Map<Brick, Integer>map = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.TEN, 2005, map);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 2006, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        em.getTransaction().begin();
        buildingKitDao.create(kit2);
        em.getTransaction().commit();
        
        List<BuildingKit> kitList = buildingKitDao.findByPrice(BigDecimal.ZERO);
        
        assertNotNull(kitList);
        assertFalse(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));

    }

    public void testFindByYearFrom() {
        System.out.println("TEST Find By Year From");
        
        Map<Brick, Integer> map = storeBricks();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 20, map);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 14, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        em.getTransaction().begin();
        buildingKitDao.create(kit2);
        em.getTransaction().commit();
        
        List<BuildingKit> kitList = buildingKitDao.findByYearFrom(15);
        
        assertNotNull(kitList);
        assertTrue(kitList.contains(kit));
        assertFalse(kitList.contains(kit2));
        assertEquals(kitList.size(), 1);

    }

    public void testFindByCategory() {
        System.out.println("TEST Find By Category");

        try {
            buildingKitDao.findByCategory(null);
            fail("finding building kits for null category");
        } catch (IllegalArgumentException ex) {
        }

        Map<Brick, Integer> map = storeBricks();

        em.getTransaction().begin();
        Category usedCategory = TestUtils.createCategory("k1", "k1");
        categoryDao.create(usedCategory);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Category nonUsedCategory = TestUtils.createCategory("k2", "k2");
        categoryDao.create(nonUsedCategory);
        em.getTransaction().commit();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 20, map);
        kit.setCategory(usedCategory);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 14, map);
        kit2.setCategory(usedCategory);
        BuildingKit kit3 = TestUtils.createBuildingKit("name3", "description", BigDecimal.ZERO, 3, map);
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        em.getTransaction().commit();
        em.getTransaction().begin();
        buildingKitDao.create(kit2);
        em.getTransaction().commit();
        em.getTransaction().begin();
        buildingKitDao.create(kit3);
        em.getTransaction().commit();

        List<BuildingKit> kitList = buildingKitDao.findByCategory(usedCategory);

        assertNotNull(kitList);
        assertTrue(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));
        assertFalse(kitList.contains(kit3));
        assertEquals(kitList.size(), 2);

        List<BuildingKit> emptyKitList = buildingKitDao.findByCategory(nonUsedCategory);

        assertNotNull(emptyKitList);
        assertEquals(emptyKitList.size(), 0);
    }

    public void testFindByBrick() {
        System.out.println("TEST Find By Brick");

        try {
            buildingKitDao.findByBrick(null);
            fail("finding bricks for null category");
        } catch (IllegalArgumentException ex) {
        }

        Map<Brick, Integer> map = storeBricks();
        Brick usedBrick = (Brick)map.keySet().toArray()[0];
        em.getTransaction().begin();
        Brick nonUsedBrick = TestUtils.createBrick("nonUsedBrick", Color.BLACK, "desc");
        brickDao.create(nonUsedBrick);
        em.getTransaction().commit();

        BuildingKit kit = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 20, map);
        BuildingKit kit2 = TestUtils.createBuildingKit("name2", "description", BigDecimal.ZERO, 14, map);
        BuildingKit kit3 = TestUtils.createBuildingKit("name3", "description", BigDecimal.ZERO, 3, new HashMap<Brick, Integer>());
        em.getTransaction().begin();
        buildingKitDao.create(kit);
        buildingKitDao.create(kit2);
        buildingKitDao.create(kit3);
        em.getTransaction().commit();

        List<BuildingKit> kitList = buildingKitDao.findByBrick(usedBrick);

        assertNotNull(kitList);
        System.out.println(kitList.size());
        assertTrue(kitList.contains(kit));
        assertTrue(kitList.contains(kit2));
        assertFalse(kitList.contains(kit3));
        assertEquals(kitList.size(), 2);

        List<BuildingKit> emptyKitList = buildingKitDao.findByBrick(nonUsedBrick);

        assertNotNull(emptyKitList);
        assertEquals(emptyKitList.size(), 0);
    }

    public Map<Brick, Integer> storeBricks(){
        Brick brick1 = TestUtils.createBrick("TestBrickA", Color.BLACK, "Test");
        Brick brick2 = TestUtils.createBrick("TestBrickB", Color.BLACK, "Test");

        em.getTransaction().begin();
        brickDao.create(brick1);
        em.getTransaction().commit();
        em.getTransaction().begin();
        brickDao.create(brick2);
        em.getTransaction().commit();
        Map<Brick, Integer> map = new HashMap<Brick, Integer>();
        map.put(brick1, 5);
        map.put(brick2, 1);
        return map;
    }
}

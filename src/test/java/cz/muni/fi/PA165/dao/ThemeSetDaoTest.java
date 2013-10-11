/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Category;
import cz.muni.fi.PA165.domain.ThemeSet;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ThemeSetDaoTest extends TestCase {

    private ThemeSetDao dao;
    private CategoryDao categoryDao;

    public ThemeSetDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        dao = new ThemeSetDaoImpl();
        categoryDao = new CategoryDaoImpl();
        dao.setEntityManager(emf.createEntityManager());
        categoryDao.setEntityManager(emf.createEntityManager());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testCreateThemeSet() {
        System.out.println("TEST CreateThemeSet");

        try {
            dao.create(null);
            fail("creating NULL building kit");
        } 
        catch (IllegalArgumentException ex) {
            
        }

        Category cat = TestUtils.createCategory("jmeno", "popis");
        categoryDao.create(cat);
        BuildingKit bk = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks = new ArrayList<BuildingKit>();
        bks.add(bk);
        ThemeSet ts1 = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.TEN, bks, cat);
        
        dao.create(ts1);
        Long id = ts1.getId();
        ThemeSet ts2 = dao.findById(id);
        
        assertEquals(ts1.getId(), ts2.getId());
        assertEquals(ts1.getBuildingKits(), ts2.getBuildingKits());
        assertEquals(ts1.getCategory(), ts2.getCategory());
        assertEquals(ts1.getDescription(), ts2.getDescription());
        assertEquals(ts1.getName(), ts2.getName());
        assertEquals(ts1.getPrice(), ts2.getPrice());
    }

    public void testRemoveThemeSet() {
        System.out.println("TEST RemoveThemeSet");
        
        try {
            dao.delete(null);
            fail("removing NULL building kit");
        }
        catch (IllegalArgumentException ex)
        {
            
        }
        
        Category cat = TestUtils.createCategory("jmeno", "popis");
        categoryDao.create(cat);
        BuildingKit bk = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks = new ArrayList<BuildingKit>();
        bks.add(bk);
        ThemeSet ts = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.TEN, bks, cat);
        
        dao.create(ts);
        dao.delete(ts.getId());
        try {
            ThemeSet tsDeleted = dao.findById(ts.getId());
            fail("deleted entity still exists");
        }
        catch (IllegalArgumentException ex) {
        }
    }

    public void testUpdateThemeSet() {
        System.out.println("TEST UpdateThemeSet");

        try {
            dao.update(null);
            fail("updating NULL theme set");
        }
        catch (IllegalArgumentException ex) {
            
        }

        Category cat = TestUtils.createCategory("jmeno", "popis");
        categoryDao.create(cat);
        BuildingKit bk = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks = new ArrayList<BuildingKit>();
        bks.add(bk);
        ThemeSet ts = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.TEN, bks, cat);

        dao.create(ts);
        
        ts.setDescription("newDescription");
        ts.setName("newName");
        ts.setPrice(BigDecimal.valueOf(25));
        Category cat2 = TestUtils.createCategory("newJmeno", "newPopis");
        categoryDao.create(cat2);

        BuildingKit bk2 = TestUtils.createBuildingKit("newName", "newDescription", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks2 = new ArrayList<BuildingKit>();
        bks2.add(bk2);
        ts.setCategory(cat2);
        ts.setBuildingKits(bks2);
        
        dao.update(ts);

        assertNotNull(ts.getId());
        assertEquals(ts.getName(), "newName");
        assertEquals(ts.getDescription(), "newDescription");
        assertEquals(ts.getPrice(), BigDecimal.valueOf(25));
        assertEquals(ts.getCategory(), cat2);
        assertTrue(ts.getBuildingKits().contains(bk2));
        assertEquals(ts.getBuildingKits().size(), 1);
    }
    
    public void testFindAll() {
        System.out.println("TEST Find All");
        
        Category cat = TestUtils.createCategory("jmeno", "popis");
        categoryDao.create(cat);
        BuildingKit bk = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks = new ArrayList<BuildingKit>();
        bks.add(bk);
        ThemeSet ts1 = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.TEN, bks, cat);
        ThemeSet ts2 = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.ZERO, bks, cat);
        
        dao.create(ts1);
        dao.create(ts2);
        
        List<ThemeSet> setList = dao.findAll();
        assertNotNull(setList);
        assertTrue(setList.contains(ts1));
        assertTrue(setList.contains(ts2));
    }
    
    public void testFindByPrice() {
        System.out.println("TEST Find By Price");
        
        Category cat = TestUtils.createCategory("jmeno", "popis");
        categoryDao.create(cat);
        BuildingKit bk = TestUtils.createBuildingKit("name", "description", BigDecimal.ZERO, 2005, null);
        ArrayList<BuildingKit> bks = new ArrayList<BuildingKit>();
        bks.add(bk);
        ThemeSet ts1 = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.TEN, bks, cat);
        ThemeSet ts2 = TestUtils.createThemeSet("Star wars", "Star wars theme set", BigDecimal.ZERO, bks, cat);
        
        dao.create(ts1);
        dao.create(ts2);
        
        List<ThemeSet> setList = dao.findByPrice(BigDecimal.ZERO);
        assertNotNull(setList);
        assertFalse(setList.contains(ts1));
        assertTrue(setList.contains(ts2));
    }
    
}

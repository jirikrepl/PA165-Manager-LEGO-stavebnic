/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.BuildingKit;
import cz.muni.fi.PA165.domain.Color;
import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 *
 * @author pc
 */
public class ThemeSetDaoTest extends TestCase {

    private ThemeSetDao dao;

    public ThemeSetDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        dao = new ThemeSetDaoImpl();
        dao.setEntityManager(emf.createEntityManager());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
/*
    /**
     * Test of createOrUpdateThemeSet method, of class ThemeSetDaoImpl.
     */
    public void testCreateOrUpdateThemeSet() {
        System.out.println("createOrUpdateThemeSet");
        ThemeSet set = TestUtils.createThemeSet("StarWars", null, new BigDecimal(500), null, null);
        //dao.createOrUpdateThemeSet(set);
    }

    /**
     * Test of removeThemeSet method, of class ThemeSetDaoImpl.
     */
    public void testRemoveThemeSet() {
        System.out.println("removeThemeSet");
        ThemeSet set = null;
        ThemeSetDaoImpl instance = new ThemeSetDaoImpl();
        instance.removeThemeSet(set);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ThemeSetDaoImpl.
     */
    public void testFindAll() {
        System.out.println("findAll");
        ThemeSetDaoImpl instance = new ThemeSetDaoImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrice method, of class ThemeSetDaoImpl.
     */
    public void testFindByPrice() {
        System.out.println("findByPrice");
        BigDecimal price = null;
        ThemeSetDaoImpl instance = new ThemeSetDaoImpl();
        List expResult = null;
        List result = instance.findByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

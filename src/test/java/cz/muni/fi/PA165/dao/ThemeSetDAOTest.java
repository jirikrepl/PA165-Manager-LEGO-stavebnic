/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author pc
 */
public class ThemeSetDAOTest extends TestCase {
    
    public ThemeSetDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createOrUpdateThemeSet method, of class ThemeSetDAO.
     */
    public void testCreateOrUpdateThemeSet() {
        System.out.println("createOrUpdateThemeSet");
        ThemeSet set = null;
        ThemeSetDAO instance = new ThemeSetDAO();
        instance.createOrUpdateThemeSet(set);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeThemeSet method, of class ThemeSetDAO.
     */
    public void testRemoveThemeSet() {
        System.out.println("removeThemeSet");
        ThemeSet set = null;
        ThemeSetDAO instance = new ThemeSetDAO();
        instance.removeThemeSet(set);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ThemeSetDAO.
     */
    public void testFindAll() {
        System.out.println("findAll");
        ThemeSetDAO instance = new ThemeSetDAO();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrice method, of class ThemeSetDAO.
     */
    public void testFindByPrice() {
        System.out.println("findByPrice");
        BigDecimal price = null;
        ThemeSetDAO instance = new ThemeSetDAO();
        List expResult = null;
        List result = instance.findByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

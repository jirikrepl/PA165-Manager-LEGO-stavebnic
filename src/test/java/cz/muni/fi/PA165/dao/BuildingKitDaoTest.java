/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.BuildingKit;
import java.math.BigDecimal;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author pc
 */
public class BuildingKitDaoTest extends TestCase {
    
    public BuildingKitDaoTest(String testName) {
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
     * Test of CreateBuildingKit method, of class BuildingKitDao.
     */
    public void testCreateBuildingKit() {
        System.out.println("CreateBuildingKit");
        BuildingKit buildingKit = null;
        BuildingKitDao instance = new BuildingKitDao();
        instance.CreateBuildingKit(buildingKit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RemoveBuildingKit method, of class BuildingKitDao.
     */
    public void testRemoveBuildingKit() {
        System.out.println("RemoveBuildingKit");
        BuildingKit buildingKit = null;
        BuildingKitDao instance = new BuildingKitDao();
        instance.RemoveBuildingKit(buildingKit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateBuildingKit method, of class BuildingKitDao.
     */
    public void testUpdateBuildingKit() {
        System.out.println("UpdateBuildingKit");
        BuildingKit buildingKit = null;
        BuildingKitDao instance = new BuildingKitDao();
        instance.UpdateBuildingKit(buildingKit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class BuildingKitDao.
     */
    public void testFindAll() {
        System.out.println("findAll");
        BuildingKitDao instance = new BuildingKitDao();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByPrice method, of class BuildingKitDao.
     */
    public void testFindByPrice() {
        System.out.println("findByPrice");
        BigDecimal price = null;
        BuildingKitDao instance = new BuildingKitDao();
        List expResult = null;
        List result = instance.findByPrice(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByYearFrom method, of class BuildingKitDao.
     */
    public void testFindByYearFrom() {
        System.out.println("findByYearFrom");
        int yearFrom = 0;
        BuildingKitDao instance = new BuildingKitDao();
        List expResult = null;
        List result = instance.findByYearFrom(yearFrom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

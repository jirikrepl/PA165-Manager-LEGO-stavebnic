/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.AbstractIntegrationTest;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import static org.mockito.Mockito.*;

/**
 *
 * @author PALO
 */
public class BuildingKitServiceTest extends AbstractIntegrationTest{
    
    private BuildingKitDao kitDao;
    private BuildingKitService kitService;
    
    @Override 
    protected void setUp() throws Exception{
        kitDao = mock(BuildingKitDao.class);
        kitService = new BuildingKitServiceImpl();
    }
    
    //TODO Will be done 
}

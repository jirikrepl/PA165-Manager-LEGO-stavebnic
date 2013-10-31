/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.AbstractIntegrationTest;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.util.Random;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

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
        kitService.setBuildingKitDao(kitDao);
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }
    
    public void testCreate(){
        System.out.println("Testing method CREATE od buildingkitService");
        try{
            kitService.create(null);
            fail();
        } catch (DataAccessException daex){
            
        }
        
        verify(kitDao, never()).create(null);
        
        BuildingKitDto kitDto = createBuildingKitDto("name","desc");
        kitService.create(kitDto);
        //Tom, prosim vytvor v cz.muni.fi.PA165.daoDtoConversion takuto staticku triedu
        //BuildingKit kit = BuildingKitConversion.convertToEntity(kitDto);
        //verify(kitDao).create(kit);
    }
    
    /*
     * helper method for creating BuildingKitDto
     */
    private BuildingKitDto createBuildingKitDto(String name, String desc){
        BuildingKitDto dto = new BuildingKitDto();
        Random random = new Random(System.currentTimeMillis());
        Long id = random.nextLong();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(desc);
        return dto;
    }
    //TODO Will be done 
}

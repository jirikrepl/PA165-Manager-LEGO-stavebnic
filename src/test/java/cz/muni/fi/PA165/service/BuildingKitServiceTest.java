/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;


import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.dao.DaoException;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.util.Random;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author PALO
 */
public class BuildingKitServiceTest extends TestCase{
    
    private BuildingKitDao kitDao;
    private BuildingKitService kitService;
    
    @Override 
    protected void setUp() throws Exception{
        kitDao = mock(BuildingKitDao.class);
        kitService = new BuildingKitServiceImpl();
        kitService.setBuildingKitDao(kitDao);
        
    }
    
    @Override
    protected void tearDown() throws Exception{
        
    }
    
    public void testCreate(){
        System.out.println("Testing method CREATE od buildingkitService");
        try{
            kitService.create(null);
            fail();
        } catch (DaoException daex){
            
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
    
    public void testUpdate(){
        System.out.println("Testing method UPDATE of buildingkitService");
        try{
            kitService.update(null);
            fail();
        } catch (DaoException dex){
            
        }
        BuildingKitDto dto = createBuildingKitDto("name","desc");
        kitService.create(dto);
        verify(kitService).create(dto);
        verify(kitDao).create(BuildingKitConversion.convertToEntity(dto));
        
        //update
        dto.setName("name2");
        kitService.update(dto);
        verify(kitService).update(dto);
        verify(kitDao).update(BuildingKitConversion.convertToEntity(dto));
        
        //if the service.findById is called, it is expected, that it will be 
        // called the dao.getId in the dao layer
        Long id = dto.getId();
        //is this really not redundant?
        when(kitDao.findById(id)).thenReturn(BuildingKitConversion.convertToEntity(dto));
        BuildingKitDto retrievedDto = kitService.findById(id);
        assertEquals(retrievedDto, dto);
    }
    public void testDelete() {
        System.out.println("testing DELETE method of BuildingKitService");
        // test null argument
        try {
            kitService.delete(null);
            fail();
        } catch (DaoException ex) {
        }
        
        BuildingKitDto dto = createBuildingKitDto("name", "desc");
        kitService.delete(dto.getId());
        Long id = dto.getId();
        verify(kitDao).delete(id);
        
        when(kitDao.findById(id)).thenReturn(null);
        BuildingKitDto deleted = kitService.findById(id);
        assertNull(deleted);
    }
    
    //TODO Will be done 
    
}

//public class BuildingKitServiceTest extends TestCase{
//
//    private BuildingKitDao kitDao;
//    private BuildingKitService kitService;
//
//    @Override
//    protected void setUp() throws Exception{
//        kitDao = mock(BuildingKitDao.class);
//        kitService = new BuildingKitServiceImpl();
//    }
//
//    //TODO Will be done
//}

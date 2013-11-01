/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;


import cz.muni.fi.PA165.dao.BuildingKitDao;

import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        } catch (IllegalArgumentException daex){
            
        }
        
        verify(kitDao, never()).create(null);
        
        BuildingKitDto kitDto = createBuildingKitDto("name","desc");
        kitService.create(kitDto);
        
        BuildingKit kit = BuildingKitConversion.convertToEntity(kitDto);
        verify(kitDao).create(kit);
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
        } catch (IllegalArgumentException dex){
            
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
        } catch (IllegalArgumentException ex) {
        }
        
        BuildingKitDto dto = createBuildingKitDto("name", "desc");
        kitService.delete(dto.getId());
        Long id = dto.getId();
        verify(kitDao).delete(id);
        
        when(kitDao.findById(id)).thenReturn(null);
        BuildingKitDto deleted = kitService.findById(id);
        assertNull(deleted);
    }
    
    public void testFindAll(){
        System.out.println("testing FINDALL method of BuildingKitService");
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        //test, when no entities are in table
        when(kitDao.findAll()).thenReturn(new ArrayList<BuildingKit>());
        List<BuildingKitDto> returnedBuildingKitDtoList = kitService.findAll();
        
        assertEquals(buildingKitDtoList, returnedBuildingKitDtoList);
        
        
        BuildingKitDto firstBKDto = createBuildingKitDto("name1","desc1");
        BuildingKitDto secondBKDto = createBuildingKitDto("name2","desc2");
        buildingKitDtoList.add(firstBKDto);
        buildingKitDtoList.add(secondBKDto);
     
        BuildingKit firstEntity = new BuildingKit();
        firstEntity.setName("name1");
        firstEntity.setDescription("desc1");
        BuildingKit secondEntity = new BuildingKit();
        secondEntity.setName("name2");
        secondEntity.setDescription("desc2");
        List<BuildingKit> entities = new ArrayList<BuildingKit>();
        entities.add(firstEntity);
        entities.add(secondEntity);
        
        when(kitDao.findAll()).thenReturn(entities);
        returnedBuildingKitDtoList = kitService.findAll();
        assertEquals(buildingKitDtoList, returnedBuildingKitDtoList);
        
    }
    
    public void testFindById(){
        System.out.println("testing method FINDBYID");
        try {
            kitService.findById(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
        BuildingKitDto dto = createBuildingKitDto("name1","desc1");
        when(kitDao.findById(dto.getId())).thenReturn(null);
        BuildingKitDto returnedDto = kitService.findById(dto.getId());
        assertNull(returnedDto);
        
        when(kitDao.findById(dto.getId())).thenReturn(BuildingKitConversion.convertToEntity(dto));
        returnedDto = kitService.findById(dto.getId());
        verify(kitDao, times(2)).findById(dto.getId());
        assertEquals(returnedDto, dto);
    }
    
    public void testFindByPrice(){
        System.out.println("testinf FINDBYPRICE method of BuildingKitService");
        
        try {
            kitService.findById(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
        verify(kitDao, never()).findByPrice(null);
        
        BigDecimal nonExistingPrice = BigDecimal.valueOf(151.151);
        when(kitDao.findByPrice(nonExistingPrice)).thenReturn(new ArrayList<BuildingKit>());
        List<BuildingKitDto> returnedDtoList = kitService.findByPrice(nonExistingPrice);
        List<BuildingKitDto> emptyDtoList = new ArrayList<BuildingKitDto>();
        assertEquals(returnedDtoList, emptyDtoList);
        
        BigDecimal existingPrice = BigDecimal.valueOf(100);
        BuildingKitDto bkDto2 = new BuildingKitDto();
        bkDto2.setName("Sada s existujici cenou");
        bkDto2.setPrice(existingPrice);

        List<BuildingKitDto> existingList = new ArrayList<BuildingKitDto>();
        existingList.add(bkDto2);

        List<BuildingKit> entityList = new ArrayList<BuildingKit>();
        entityList.add(BuildingKitConversion.convertToEntity(bkDto2));
        when(kitDao.findByPrice(existingPrice)).thenReturn(entityList);
        
        assertEquals(existingList, kitService.findByPrice(existingPrice));
        
        verify(kitDao, times(1)).findByPrice(nonExistingPrice);
        verify(kitDao, times(1)).findByPrice(existingPrice);
    
    }
    
    public void testFindByYearFrom(){
        System.out.println("testinf FINDBYYEARFROM method of BuildingKitService");
        
        try {
            kitService.findById(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
    }
    //TODO Will be done 
    
}



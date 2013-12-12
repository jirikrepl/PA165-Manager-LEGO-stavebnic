package cz.muni.fi.PA165.service;

import com.sun.org.apache.xpath.internal.Arg;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.entity.BuildingKit;
import junit.framework.TestCase;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
/**
 *
 * @author Pavol Bako
 */
public class BuildingKitServiceTest extends TestCase {
 
    private BuildingKitDao kitDao;
    private BuildingKitServiceImpl kitService;
 
    @Override
    protected void setUp() throws Exception {
        kitDao = mock(BuildingKitDao.class);
        kitService = new BuildingKitServiceImpl();
        kitService.setBuildingKitDao(kitDao);
 
    }
 
    @Override
    protected void tearDown() throws Exception {
    }
 
    public void testCreate() {
        System.out.println("CREATE");
        try {
            kitService.create(null);
            fail();
        } catch (DataAccessException e) {
        }
 
        verify(kitDao, never()).create(null);
 
        BuildingKitDto kitDto = createBuildingKitDto("name", "desc");
        kitService.create(kitDto);

        ArgumentCaptor<BuildingKit> captor = ArgumentCaptor.forClass(BuildingKit.class);
        verify(kitDao).create(captor.capture());
        assertEquals(captor.getValue().getName(), kitDto.getName());
    }
 
    /*
     * helper method for creating BuildingKitDto
     */
    private BuildingKitDto createBuildingKitDto(String name, String desc) {
        BuildingKitDto dto = new BuildingKitDto();
        Random random = new Random(System.currentTimeMillis());
        Long id = random.nextLong();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(desc);
        return dto;
    }
 
    public void testUpdate() {
        System.out.println("UPDATE");
        try {
            kitService.update(null);
            fail();
        } catch (DataAccessException e) {
        }
        BuildingKitDto dto = createBuildingKitDto("name", "desc");
        kitService.create(dto);

        ArgumentCaptor<BuildingKit> captor1 = ArgumentCaptor.forClass(BuildingKit.class);
        verify(kitDao).create(captor1.capture());
        assertEquals(captor1.getValue().getName(), dto.getName());

        //update
        dto.setName("name2");
        kitService.update(dto);

        ArgumentCaptor<BuildingKit> captor2 = ArgumentCaptor.forClass(BuildingKit.class);
        verify(kitDao).update(captor2.capture());
        assertEquals(captor2.getValue().getName(), dto.getName());

        //if the service.findById is called, it is expected, that it will be
        // called the dao.getId in the dao layer
        Long id = dto.getId();
        //is this really not redundant?
        when(kitDao.findById(id)).thenReturn(BuildingKitConversion.convertToEntity(dto));
        BuildingKitDto retrievedDto = kitService.findById(id);
        assertEquals(retrievedDto, dto);
    }
 
    public void testDelete() {
        System.out.println("DELETE");
        // test null argument
        try {
            kitService.delete(null);
            fail();
        } catch (DataAccessException e) {
        }
 
        BuildingKitDto dto = createBuildingKitDto("name", "desc");
 
        kitService.delete(new Long(1));
 
        verify(kitDao).delete(new Long(1));
    }
 
    public void testFindAll() {
        System.out.println("FINDALL");
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        //test, when no entities are in table
        when(kitDao.findAll()).thenReturn(new ArrayList<BuildingKit>());
        List<BuildingKitDto> returnedBuildingKitDtoList = kitService.findAll();
 
        assertEquals(buildingKitDtoList, returnedBuildingKitDtoList);
 
 
        BuildingKitDto firstBKDto = createBuildingKitDto("name1", "desc1");
        BuildingKitDto secondBKDto = createBuildingKitDto("name2", "desc2");
        buildingKitDtoList.add(firstBKDto);
        buildingKitDtoList.add(secondBKDto);
 
        BuildingKit firstEntity = new BuildingKit();
        firstEntity.setId(null);
        firstEntity.setName("name1");
        firstEntity.setDescription("desc1");
        BuildingKit secondEntity = new BuildingKit();
        secondEntity.setId(null);
        secondEntity.setName("name2");
        secondEntity.setDescription("desc2");
        List<BuildingKit> entities = new ArrayList<BuildingKit>();
        entities.add(firstEntity);
        entities.add(secondEntity);
 
        when(kitDao.findAll()).thenReturn(entities);
        returnedBuildingKitDtoList = kitService.findAll();
 
 
        for (BuildingKit kit : entities) {
            assertTrue(returnedBuildingKitDtoList.contains(BuildingKitConversion.convertToDto(kit)));
        }
 
    }
 
    public void testFindById() {
        System.out.println("FINDBYID");
        try {
            kitService.findById(null);
            fail();
        } catch (DataAccessException e) {
        }
 
        BuildingKitDto dto = createBuildingKitDto("name1", "desc1");
        doThrow(new DataAccessException("Object not found") {}).when(kitDao).findById(new Long(-1));
 
        try {
            kitService.findById(new Long(-1));
            fail();
        } catch (DataAccessException e) {
        }
 
        when(kitDao.findById(dto.getId())).thenReturn(BuildingKitConversion.convertToEntity(dto));
 
 
 
        BuildingKitDto returnedDto = kitService.findById(dto.getId());
        verify(kitDao, times(1)).findById(dto.getId());
        assertEquals(returnedDto, dto);
    }
    
    public void testFindByCategory(){
        
    }
    public void testFindByThemeSet(){
        
    }
    public void testFindBrick(){    
        
    }
    
}
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.daoDtoConversion.BrickConversion;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import junit.framework.TestCase;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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
        Long id = new Long(5);
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
        try {
            kitService.findByCategory(null);
            fail();
        } catch (DataAccessException e) {

        }
        verify(kitDao, never()).findByCategory(null);

        CategoryDto nonExistingCategoryDto = new CategoryDto();
        Category nonExistingCategory = CategoryConversion.convertToEntity(nonExistingCategoryDto);

        when(kitDao.findByCategory(nonExistingCategory)).thenReturn(new ArrayList<BuildingKit>());
        ArrayList<BuildingKit> emptyList = new ArrayList<BuildingKit>();
        assertEquals(emptyList, kitService.findByCategory(nonExistingCategoryDto));

        ArgumentCaptor<Category> captor1 = ArgumentCaptor.forClass(Category.class);
        verify(kitDao, times(1)).findByCategory(captor1.capture());
        assertEquals(captor1.getValue().getName(), nonExistingCategory.getName());

        CategoryDto existingCategoryDto = new CategoryDto();
        Category existingCategory = CategoryConversion.convertToEntity(existingCategoryDto);

        List<BuildingKitDto> existingList = new ArrayList<BuildingKitDto>();

        BuildingKitDto kitWithExistingCategory = createBuildingKitDto("bk with cat", "desc");
        existingList.add(kitWithExistingCategory);

        List<BuildingKit> entityList = new ArrayList<BuildingKit>();
        entityList.add(BuildingKitConversion.convertToEntity(kitWithExistingCategory));
        when(kitDao.findByCategory(existingCategory)).thenReturn(entityList);

        List<BuildingKitDto> resultList = kitService.findByCategory(existingCategoryDto);
        ArgumentCaptor<Category> captor2 = ArgumentCaptor.forClass(Category.class);
        verify(kitDao, atLeastOnce()).findByCategory(captor2.capture());
        assertEquals(captor2.getValue().getName(), existingCategory.getName());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(existingList.get(i).getId(), resultList.get(i).getId());
            assertEquals(existingList.get(i).getName(), resultList.get(i).getName());
        }
    }

    public void testFindByThemeSet(){
        try {
            kitService.findByThemeSet(null);
            fail();
        } catch (DataAccessException e) {

        }
        verify(kitDao, never()).findByThemeSet(null);

        ThemeSetDto nonExistingThemeSetDto = new ThemeSetDto();
        ThemeSet nonExistingThemeSet = ThemeSetConversion.convertToEntity(nonExistingThemeSetDto);

        when(kitDao.findByThemeSet(nonExistingThemeSet)).thenReturn(new ArrayList<BuildingKit>());
        ArrayList<BuildingKit> emptyList = new ArrayList<BuildingKit>();
        assertEquals(emptyList, kitService.findByThemeSet(nonExistingThemeSetDto));

        ArgumentCaptor<ThemeSet> captor1 = ArgumentCaptor.forClass(ThemeSet.class);
        verify(kitDao, times(1)).findByThemeSet(captor1.capture());
        assertEquals(captor1.getValue().getName(), nonExistingThemeSet.getName());

        ThemeSetDto existingThemeSetDto = new ThemeSetDto();
        ThemeSet existingThemeSet = ThemeSetConversion.convertToEntity(existingThemeSetDto);

        List<BuildingKitDto> existingList = new ArrayList<BuildingKitDto>();

        BuildingKitDto kitWithExistingThemeSet = createBuildingKitDto("bk with ts", "desc");
        existingList.add(kitWithExistingThemeSet);

        List<BuildingKit> entityList = new ArrayList<BuildingKit>();
        entityList.add(BuildingKitConversion.convertToEntity(kitWithExistingThemeSet));
        when(kitDao.findByThemeSet(existingThemeSet)).thenReturn(entityList);

        List<BuildingKitDto> resultList = kitService.findByThemeSet(existingThemeSetDto);
        ArgumentCaptor<ThemeSet> captor2 = ArgumentCaptor.forClass(ThemeSet.class);
        verify(kitDao, atLeastOnce()).findByThemeSet(captor2.capture());
        assertEquals(captor2.getValue().getName(), existingThemeSet.getName());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(existingList.get(i).getId(), resultList.get(i).getId());
            assertEquals(existingList.get(i).getName(), resultList.get(i).getName());
        }
    }

    public void testFindByBrick(){
        try {
            kitService.findByBrick(null);
            fail();
        } catch (DataAccessException e) {

        }
        verify(kitDao, never()).findByBrick(null);

        BrickDto nonExistingBrickDto = new BrickDto();
        Brick nonExistingBrick = BrickConversion.convertToEntity(nonExistingBrickDto);

        when(kitDao.findByBrick(nonExistingBrick)).thenReturn(new ArrayList<BuildingKit>());
        ArrayList<BuildingKit> emptyList = new ArrayList<BuildingKit>();
        assertEquals(emptyList, kitService.findByBrick(nonExistingBrickDto));

        ArgumentCaptor<Brick> captor1 = ArgumentCaptor.forClass(Brick.class);
        verify(kitDao, times(1)).findByBrick(captor1.capture());
        assertEquals(captor1.getValue().getName(), nonExistingBrick.getName());

        BrickDto existingBrickDto = new BrickDto();
        Brick existingBrick = BrickConversion.convertToEntity(existingBrickDto);

        List<BuildingKitDto> existingList = new ArrayList<BuildingKitDto>();

        BuildingKitDto kitWithExistingBrick= createBuildingKitDto("bk with brick", "desc");
        existingList.add(kitWithExistingBrick);

        List<BuildingKit> entityList = new ArrayList<BuildingKit>();
        entityList.add(BuildingKitConversion.convertToEntity(kitWithExistingBrick));
        when(kitDao.findByBrick(existingBrick)).thenReturn(entityList);

        List<BuildingKitDto> resultList = kitService.findByBrick(existingBrickDto);
        ArgumentCaptor<Brick> captor2 = ArgumentCaptor.forClass(Brick.class);
        verify(kitDao, atLeastOnce()).findByBrick(captor2.capture());
        assertEquals(captor2.getValue().getName(), existingBrick.getName());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(existingList.get(i).getId(), resultList.get(i).getId());
            assertEquals(existingList.get(i).getName(), resultList.get(i).getName());
        }
    }
    
}
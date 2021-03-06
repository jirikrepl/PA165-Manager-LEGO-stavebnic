package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.dao.CategoryDao;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.entity.Category;
import junit.framework.TestCase;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * test of the category class on service layer
 * 
 * @author Jiri Krepl
 */
public class CategoryServiceTest extends TestCase {

    private CategoryServiceImpl categoryService;
    private CategoryDao categoryDao;

    @Override
    protected void setUp() throws Exception {
        categoryDao = mock(CategoryDao.class);
        categoryService = new CategoryServiceImpl();
        categoryService.setCategoryDao(categoryDao);
    }

    /**
     * test create category method on service layer
     */
    public void testCreateCategory() {
        System.out.println("testing createCategory on Service layer");
        // test null argument
        try {
            categoryService.create(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        // on Dao layer, create method with null argument was never called
        // new IllegeaArgumentException was throws on service Layer method create
        verify(categoryDao, never()).create(null);
        
        // test creation with ServiceDto object
        CategoryDto categoryDto = createCategoryDto("name", "desc");
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        categoryService.create(categoryDto);
        Category category = CategoryConversion.convertToEntity(categoryDto);
        // verify that create method was called on mock categoryDao object
        verify(categoryDao).create(captor.capture());
        assertEquals(category.getName(), captor.getValue().getName());
    }

    /**
     * helper method to create CategoryDto
     */
    private CategoryDto createCategoryDto(String name, String desc) {
        CategoryDto categoryDto = new CategoryDto();
        Long id = new Long(5);
        categoryDto.setId(id);
        categoryDto.setDescription(desc);
        categoryDto.setName(name);
        return categoryDto;
    }
    
    /**
     * test update method of category class on service layer
     */
    public void testUpdateCategory() {
        System.out.println("testing updateCategory method on Service layer");
        // test null argument
        try {
            categoryService.update(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
        CategoryDto categoryDto = createCategoryDto("name", "desc");
        Long id = categoryDto.getId();
        categoryService.create(categoryDto);
        ArgumentCaptor<Category> captor1 = ArgumentCaptor.forClass(Category.class);
        verify(categoryDao).create(captor1.capture());
        assertEquals(captor1.getValue().getName(), categoryDto.getName());
        
        //update
        categoryDto.setName("name2");

        categoryService.update(categoryDto);
        ArgumentCaptor<Category> captor2 = ArgumentCaptor.forClass(Category.class);
        verify(categoryDao).update(captor2.capture());
        assertEquals(captor2.getValue().getName(), categoryDto.getName());
        
        // mock dao
        when(categoryDao.findById(id)).thenReturn(CategoryConversion.convertToEntity(categoryDto));
        CategoryDto retrievedDto = categoryService.findById(id);
        assertEquals(retrievedDto, categoryDto);


    }

    /**
     * test deletion of category Object on service layer
     */
    public void testDeleteCategory() {
        System.out.println("testing deleteCategory method on Service layer");
        // test null argument
        try {
            categoryService.delete(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
        CategoryDto categoryDto = createCategoryDto("name", "desc");
        categoryService.delete(categoryDto.getId());
        Long id = categoryDto.getId();
        verify(categoryDao).delete(id);
        
        when(categoryDao.findById(id)).thenReturn(null);
        CategoryDto deleted = categoryService.findById(id);
        assertNull(deleted);
    }

    /**
     * test find all objects on service layer
     */
    public void testFindAll() {
        System.out.println("testing findAll method on Service layer");
        // test when no entities are in table (returs empty list)
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        when(categoryDao.findAll()).thenReturn(new ArrayList<Category>());
        
        List<CategoryDto> returnedCategoryDtoList = categoryService.findAll();
        assertEquals(categoryDtoList, returnedCategoryDtoList);
        
        // return some entities
        // create list of dto objects
        CategoryDto firstDto = createCategoryDto("name1", "desc1");
        CategoryDto secondDto = createCategoryDto("name2", "desc2");
        categoryDtoList.add(firstDto);
        categoryDtoList.add(secondDto);
        
        // create list of category entity objects
        Category firstEntity = new Category();
        firstEntity.setId(firstDto.getId());
        firstEntity.setName("name1");
        firstEntity.setDescription("desc1");
        Category secondEntity = new Category();
        secondEntity.setId(secondDto.getId());
        secondEntity.setName("name2");
        secondEntity.setDescription("desc2");
        List<Category> entities = new ArrayList<Category>();
        entities.add(firstEntity);
        entities.add(secondEntity);
        when(categoryDao.findAll()).thenReturn(entities);
        List<CategoryDto> categoryDtoList1 = categoryService.findAll();
        assertEquals(categoryDtoList1, categoryDtoList);
        
    }

    /**
     * test findById method on service Layer
     */
    public void testFindById() {
        System.out.println("testing findAll method on Service layer");
        // test null argument
        try {
            categoryService.findById(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }

        CategoryDto categoryDto = createCategoryDto("name1", "desc1");
        
        // test, when no entity is in table
        when(categoryDao.findById(categoryDto.getId())).thenReturn(null);
        CategoryDto returnedDto = categoryService.findById(categoryDto.getId());
        assertNull(returnedDto);
        
        // test - save some entity in table, return it by id
        when(categoryDao.findById(categoryDto.getId())).thenReturn(CategoryConversion.convertToEntity(categoryDto));
        returnedDto = categoryService.findById(categoryDto.getId());
        verify(categoryDao, times(2)).findById(categoryDto.getId());
        assertEquals(returnedDto, categoryDto);
    }
    
    /**
     * test method findByName on serviceLayer
     */
    public void testFindByName() {
        System.out.println("testing findMyName method on Service layer");
        // test null argument
        try {
            categoryService.findByName(null);
            fail();
        } catch (DataAccessExceptionService ex) {
        }
        
        CategoryDto categoryDto = createCategoryDto("name1", "desc1");
        
        // test, when no entity is in table
        when(categoryDao.findByName(categoryDto.getName())).thenReturn(null);
        CategoryDto returnedDto = categoryService.findByName(categoryDto.getName());
        assertNull(returnedDto);
        
        // test - save some entity in table, return it by id
        when(categoryDao.findByName(categoryDto.getName())).thenReturn(CategoryConversion.convertToEntity(categoryDto));
        returnedDto = categoryService.findByName(categoryDto.getName());
        verify(categoryDao, times(2)).findByName(categoryDto.getName());
        assertEquals(returnedDto, categoryDto);
    }
}

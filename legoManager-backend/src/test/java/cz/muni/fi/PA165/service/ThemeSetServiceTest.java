/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.dao.ThemeSetDao;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Tomas Kopecky
 */
public class ThemeSetServiceTest {
    
    private ThemeSetDao dao;
    private ThemeSetServiceImpl service;
    
    public ThemeSetServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = mock(ThemeSetDao.class);
        service = new ThemeSetServiceImpl();
        service.setThemeSetDao(dao);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTodo() {
        assert(true);
    }

    /**
     * helper method to create ThemeSetDto
     */
    private ThemeSetDto createThemeSetDto(String name) {
        ThemeSetDto themeSetDto = new ThemeSetDto();
        Random random = new Random(System.currentTimeMillis());
        Long id = random.nextLong();
        themeSetDto.setId(id);
        themeSetDto.setName(name);
        return themeSetDto;
    }

    /**
     * Test of create method, of class ThemeSetService.
     */
    @Test
    public void testCreate() {
        try {
            service.create(null);
            fail();
        } catch (DataAccessException ex) { }
        verify(dao, never()).create(null);

        ThemeSetDto themeSetDto = createThemeSetDto("ts1");
        ArgumentCaptor<ThemeSet> captor = ArgumentCaptor.forClass(ThemeSet.class);
        service.create(themeSetDto);
        ThemeSet themeSet = ThemeSetConversion.convertToEntity(themeSetDto);
        verify(dao).create(captor.capture());
        assertEquals(themeSet.getName(), captor.getValue().getName());
    }

    /**
     * Test of update method, of class ThemeSetService.
     */
    @Test
    public void testUpdate() {
        try {
            service.update(null);
            fail();
        } catch (DataAccessException ex) {

        }
        verify(dao, never()).update(null);

        ThemeSetDto themeSetDto = createThemeSetDto("ts1");
        Long id = themeSetDto.getId();

        service.create(themeSetDto);
        ArgumentCaptor<ThemeSet> captor1 = ArgumentCaptor.forClass(ThemeSet.class);
        verify(dao).create(captor1.capture());
        assertEquals(captor1.getValue().getName(), themeSetDto.getName());

        //update
        themeSetDto.setName("ts2");
        service.update(themeSetDto);
        ArgumentCaptor<ThemeSet> captor2 = ArgumentCaptor.forClass(ThemeSet.class);
        verify(dao).update(captor2.capture());
        assertEquals(captor2.getValue().getName(), themeSetDto.getName());

        // mock dao
        when(dao.findById(id)).thenReturn(ThemeSetConversion.convertToEntity(themeSetDto));
        ThemeSetDto retrievedDto = service.findById(id);
        assertEquals(retrievedDto, themeSetDto);
    }

     /**
     * Test of delete method, of class ThemeSetService.
     */
    @Test
    public void testDelete() {
        try {
            service.delete(null);
            fail();
        } catch (DataAccessException ex) {

        }
        verify(dao, never()).delete(null);

        ThemeSetDto themeSetDto = createThemeSetDto("name");
        service.delete(themeSetDto.getId());
        Long id = themeSetDto.getId();
        verify(dao).delete(id);

        when(dao.findById(id)).thenReturn(null);
        ThemeSetDto deleted = service.findById(id);
        assertNull(deleted);
    }

    /**
     * Test of findAll method, of class ThemeSetService.
     */
    @Test
    public void testFindAll() {
        when(dao.findAll()).thenReturn(new ArrayList<ThemeSet>());
        assertEquals(new ArrayList<ThemeSet>(), service.findAll());
        verify(dao).findAll();

        ThemeSetDto tsDto1 = new ThemeSetDto();
        tsDto1.setName("Sada 1");
        ThemeSetDto tsDto2 = new ThemeSetDto();
        tsDto2.setName("Sada 2");

        List<ThemeSetDto> list = new ArrayList<ThemeSetDto>();
        list.add(tsDto1);
        list.add(tsDto2);

        List<ThemeSet> listEntities = new ArrayList<ThemeSet>();
        listEntities.add(ThemeSetConversion.convertToEntity(tsDto1));
        listEntities.add(ThemeSetConversion.convertToEntity(tsDto2));

        when(dao.findAll()).thenReturn(listEntities);

        assertEquals(list, service.findAll());

        verify(dao, times(2)).findAll();
    }

    /**
     * Test of findById method, of class ThemeSetService.
     */
    @Test
    public void testFindById() {
        try {
            service.findById(null);
            fail();
        } catch (DataAccessException e) {

        }
        verify(dao, never()).create(null);

        Long nonExistingId = 5656L;

        when(dao.findById(nonExistingId)).thenThrow(new DataAccessException("ID cannot be NULL") {});
        try {
            ThemeSetDto returnedThemeSet = service.findById(nonExistingId);
        }
        catch (DataAccessException e) {

        }

        Long existingId = 0L;
        ThemeSetDto tsDto = new ThemeSetDto();
        tsDto.setName("Sada s existujicim Id");
        tsDto.setId(existingId);

        when(dao.findById(existingId)).thenReturn(ThemeSetConversion.convertToEntity(tsDto));

        assertEquals(tsDto, service.findById(existingId));


        verify(dao, times(1)).findById(nonExistingId);
        verify(dao, times(1)).findById(existingId);
    }

    /**
     * Test of findByCategory method, of class ThemeSetService.
     */
    @Test
    public void testFindByCategory() {
        try {
            service.findByCategory(null);
            fail();
        } catch (DataAccessException e) {

        }
        verify(dao, never()).findByCategory(null);

        CategoryDto nonExistingCategoryDto = new CategoryDto();
        Category nonExistingCategory = CategoryConversion.convertToEntity(nonExistingCategoryDto);

        when(dao.findByCategory(nonExistingCategory)).thenReturn(new ArrayList<ThemeSet>());
        ArrayList<ThemeSet> emptyList = new ArrayList<ThemeSet>();
        assertEquals(emptyList, service.findByCategory(nonExistingCategoryDto));

        ArgumentCaptor<Category> captor1 = ArgumentCaptor.forClass(Category.class);
        verify(dao, times(1)).findByCategory(captor1.capture());
        assertEquals(captor1.getValue().getName(), nonExistingCategory.getName());

        CategoryDto existingCategoryDto = new CategoryDto();
        Category existingCategory = CategoryConversion.convertToEntity(existingCategoryDto);

        List<ThemeSetDto> existingList = new ArrayList<ThemeSetDto>();

        ThemeSetDto themeSetWithExistingCategory = createThemeSetDto("ts with category");
        existingList.add(themeSetWithExistingCategory);

        List<ThemeSet> entityList = new ArrayList<ThemeSet>();
        entityList.add(ThemeSetConversion.convertToEntity(themeSetWithExistingCategory));
        when(dao.findByCategory(existingCategory)).thenReturn(entityList);

        List<ThemeSetDto> resultList = service.findByCategory(existingCategoryDto);
        ArgumentCaptor<Category> captor2 = ArgumentCaptor.forClass(Category.class);
        verify(dao, times(1)).findByCategory(captor2.capture());
        assertEquals(captor2.getValue().getName(), existingCategory.getName());

        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(existingList.get(i).getId(), resultList.get(i).getId());
            assertEquals(existingList.get(i).getName(), resultList.get(i).getName());
        }
    }
}

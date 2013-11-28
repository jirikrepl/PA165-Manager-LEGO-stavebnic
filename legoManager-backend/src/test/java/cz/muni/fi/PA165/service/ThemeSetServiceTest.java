/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.TestUtils;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.dao.ThemeSetDao;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

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
        
        ThemeSetDto themeSetDto = new ThemeSetDto();
        doNothing().when(dao).update(ThemeSetConversion.convertToEntity(themeSetDto));

        service.create(themeSetDto);
        verify(dao).create(ThemeSetConversion.convertToEntity(themeSetDto));
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
        
        ThemeSetDto themeSetDto = new ThemeSetDto();
        doNothing().when(dao).update(ThemeSetConversion.convertToEntity(themeSetDto));

        service.update(themeSetDto);
        verify(dao).update(ThemeSetConversion.convertToEntity(themeSetDto));
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
        
        doNothing().when(dao).delete(1L);
        service.delete(1L);

        verify(dao).delete(1L);
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
     * Test of findByPrice method, of class ThemeSetService.
     */
    @Test
    public void testFindByPrice() {
        try {
            service.findByPrice(null);
            fail();
        } catch (DataAccessException e) {
            
        }
        verify(dao, never()).findByPrice(null);

        BigDecimal nonExistingPrice = BigDecimal.valueOf(151.151);
        ThemeSetDto tsDto = new ThemeSetDto();
        tsDto.setName("Sada s neexistujici cenou");
        tsDto.setPrice(nonExistingPrice);

        when(dao.findByPrice(BigDecimal.valueOf(151.151))).thenReturn(new ArrayList<ThemeSet>());
        ArrayList<ThemeSet> emptyList = new ArrayList<ThemeSet>();
        assertEquals(emptyList, service.findByPrice(nonExistingPrice));
        
        BigDecimal existingPrice = BigDecimal.valueOf(100);
        ThemeSetDto tsDto2 = new ThemeSetDto();
        tsDto2.setName("Sada s existujici cenou");
        tsDto2.setPrice(existingPrice);

        List<ThemeSetDto> existingList = new ArrayList<ThemeSetDto>();
        existingList.add(tsDto2);

        List<ThemeSet> entityList = new ArrayList<ThemeSet>();
        entityList.add(ThemeSetConversion.convertToEntity(tsDto2));
        when(dao.findByPrice(existingPrice)).thenReturn(entityList);


        assertEquals(existingList, service.findByPrice(existingPrice));


        verify(dao, times(1)).findByPrice(nonExistingPrice);
        verify(dao, times(1)).findByPrice(existingPrice);
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

        CategoryDto existingCategoryDto = new CategoryDto();
        Category existingCategory = CategoryConversion.convertToEntity(existingCategoryDto);

        List<ThemeSetDto> existingList = new ArrayList<ThemeSetDto>();
        ThemeSetDto themeSetWithExistingCategory = new ThemeSetDto();
        existingList.add(themeSetWithExistingCategory);

        List<ThemeSet> entityList = new ArrayList<ThemeSet>();
        entityList.add(ThemeSetConversion.convertToEntity(themeSetWithExistingCategory));
        when(dao.findByCategory(existingCategory)).thenReturn(entityList);

        assertEquals(existingList, service.findByCategory(existingCategoryDto));

        verify(dao, times(1)).findByCategory(nonExistingCategory);
        verify(dao, times(1)).findByCategory(existingCategory);
    }
}

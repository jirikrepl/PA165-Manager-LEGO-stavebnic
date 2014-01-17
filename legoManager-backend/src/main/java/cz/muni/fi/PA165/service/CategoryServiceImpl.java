package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.service.CategoryService;
import cz.muni.fi.PA165.dao.CategoryDao;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        if (categoryDao == null) {
            throw new DataAccessExceptionService("CategoryDao cannot be NULL");
        }

        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> brickEntitites = categoryDao.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category category : brickEntitites) {
            categoryDtoList.add(CategoryConversion.conversionToDto(category));
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto findByName(String name) {
        if(name == null) {
            throw new DataAccessExceptionService("parameter name cannot be NULL");
        }
        Category category = categoryDao.findByName(name);

        CategoryDto dto = null;
        try {
            dto = CategoryConversion.conversionToDto(category);
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DataAccessExceptionService("DTO object cannot be NULL");
        }
        Category category = CategoryConversion.convertToEntity(categoryDto);
        categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        categoryDao.delete(id);
    }

    @Override
    public void create(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DataAccessExceptionService("DTO object cannot be NULL");
        }
        Category categoryEntity = CategoryConversion.convertToEntity(categoryDto);
        categoryDao.create(categoryEntity);
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        Category entity = categoryDao.findById(id);
        
        CategoryDto dto = null;
        try {
            dto = CategoryConversion.conversionToDto(entity);
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }
}

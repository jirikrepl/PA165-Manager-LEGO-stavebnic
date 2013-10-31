package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.CategoryDao;
import cz.muni.fi.PA165.dao.DaoException;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.dto.CategoryDto;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> brickEntitites = categoryDao.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category category : brickEntitites) {
            categoryDtoList.add(category.createDto());
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto findByName(String name) {
        if(name == null) {
            throw new DaoException("error - category cannot be null");
        }
        Category category = categoryDao.findByName(name);

        CategoryDto dto = null;
        try {
            dto = category.createDto();
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DaoException("error - category cannot be null");
        }
        Category category = categoryDto.createEntity();
        categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            throw new DaoException("error - category cannot be null");
        }
        categoryDao.delete(id);
    }

    @Override
    public void create(CategoryDto categoryDto) {
        if(categoryDto == null) {
            throw new DaoException("error - category cannot be null");
        }
        Category categoryEntity = categoryDto.createEntity();
        categoryDao.create(categoryEntity);
    }

    @Override
    public CategoryDto findById(Long id) {
        if(id == null) {
            throw new DaoException("error - category cannot be null");
        }
        Category entity = categoryDao.findById(id);
        
        CategoryDto dto = null;
        try {
            dto = entity.createDto();
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }
}

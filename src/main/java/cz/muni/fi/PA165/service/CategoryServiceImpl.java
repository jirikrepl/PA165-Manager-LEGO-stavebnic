package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.CategoryDao;
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
        Category category = categoryDao.findByName(name);

        return category.createDto();
    }

    @Override
    public void update(CategoryDto categoryDto) {
        Category category = categoryDto.createEntity();
        categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public void create(CategoryDto categoryDto) {
        Category categoryEntity = categoryDto.createEntity();
        categoryDao.create(categoryEntity);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category entity = categoryDao.findById(id);
        return entity.createDto();
    }
}

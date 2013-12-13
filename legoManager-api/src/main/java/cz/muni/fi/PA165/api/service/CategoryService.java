package cz.muni.fi.PA165.api.service;

//import cz.muni.fi.PA165.dao.CategoryDao;
import cz.muni.fi.PA165.api.dto.CategoryDto;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
public interface CategoryService {

    /**
     * find all brick entities in db table
     *
     * @return List<Category> list of brick objects
     */
    public List<CategoryDto> findAll();

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    public CategoryDto findByName(String name);

    /**
     * updates given category
     *
     * @param category instance of Category entity class
     */
    public void update(CategoryDto category);

    /**
     * deletes given category
     *
     * @param id id of the instance of the category to remove
     */
    public void delete(Long id);

    /**
     * persist given category
     *
     * @param category instance of Category entity class
     */
    public void create(CategoryDto category);

    /**
     * retrieves category with given id from the database
     *
     * @param id the given id
     */
    public CategoryDto findById(Long id);

}

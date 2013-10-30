package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public interface CategoryDao {

    /**
     * find all brick entities in db table
     *
     * @return List<Category> list of brick objects
     */
    public List<Category> findAll();

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    public Category findByName(String name);

    /**
     * updates given category
     *
     * @param category instance of Category entity class
     */
    public void update(Category category);

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
    public void create(Category category);
    
    /**
     * retrieves category with given id from the database
     *
     * @param id the given id
     */
    public Category findById(Long id);

    /**
     * sets the EntityManager
     *
     * @param EntityManager takes entityManager as parameter
     */
    public void setEntityManager(EntityManager entityManager);
    
}

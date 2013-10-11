package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Category;

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

    public void update(Category category);

    public void delete(Long id);

    public void create(Category category);
    
    public Category findById(Long id);

    public void setEntityManager(EntityManager entityManager);
    
}

package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Category;

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
    public List<Category> findByName(String name);

    public void update(Category category);

    public void delete(Category category);

    public void create(Category category);

    public void close();

}

package cz.muni.fi.PA165.daoInterface;

import cz.muni.fi.PA165.domain.Category;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
public interface CategoryDaoInterface {

    public void storeCategory(Category category);

    /**
     * remove category entity from table
     *
     * @param category instance of brick entity class, which has to be removed
     */
    public void removeCategory(Category category);

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

}

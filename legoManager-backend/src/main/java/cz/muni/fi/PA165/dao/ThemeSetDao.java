package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public interface ThemeSetDao {


    /**
     * finds all ThemeSet entities in DB
     *
     * @return List<ThemeSet> all entities in DB
     */
    public List<ThemeSet> findAll();

    /**
     * finds entities in DB with given price
     *
     * @return List<ThemeSet> of all entities with given price
     */
    public List<ThemeSet> findByPrice(BigDecimal price);

    /**
     * sets the EntityManager
     *
     * @param EntityManager takes entityManager as parameter
     */
    public void setEntityManager(EntityManager entityManager);

    /**
     * updates the ThemeSet in DB
     *
     * @param ThemeSet takes ThemeSet as parameter
     */
    public void update(ThemeSet set);

    /**
     * deletes the ThemeSet in DB according to ID
     *
     * @param Long
     */
    public void delete(Long id);

    /**
     * creates ThemeSet entity in DB
     *
     * @param ThemeSet
     */
    public void create(ThemeSet set);

    /**
     * finds ThemeSet entity according to ID
     *
     * @param Long
     * @return ThemeSet
     */
    public ThemeSet findById(Long id);

    /**
     * finds ThemeSet entities which are connected to the specified category
     *
     * @param category
     * @return List<ThemeSet>
     */
    //public List<ThemeSet> findByCategory(Category category);
}

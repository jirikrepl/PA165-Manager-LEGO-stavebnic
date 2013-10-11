package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.ThemeSet;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public interface ThemeSetDao {
    
     
    /**
     * finds all entities in DB
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

    public void setEntityManager(EntityManager entityManager);
    
    public void update(ThemeSet set);

    public void delete(ThemeSet set);

    public void create(ThemeSet set);

    public void close();
    
}

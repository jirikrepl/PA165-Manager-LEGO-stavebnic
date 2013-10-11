package cz.muni.fi.PA165.dao;

import static cz.muni.fi.PA165.dao.Dao.entityManager;
import cz.muni.fi.PA165.daoInterface.ThemeSetDaoInterface;
import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;

/**
 * DAO class for ThemeSet
 *
 * @author Pavol Bako
 */
public class ThemeSetDAO extends Dao<ThemeSet> implements ThemeSetDaoInterface{

    /**
     * uses superclass constructor
     */
    public ThemeSetDAO(){
        super();
    }
    
    /**
     * creates and updates ThemeSet instance
     *
     * @param set instance of ThemeSet entity class, which has to be changed
     */
    public void createOrUpdateThemeSet(ThemeSet set) {
        store(set);
    }

    /**
     * removes the ThemeSet instance
     *
     * @param set instance of ThemeSet entity class
     */
    public void removeThemeSet(ThemeSet set) {
        delete(set);
    }

    /**
     * finds all entities in DB
     *
     * @return List<ThemeSet> all entities in DB
     */
    public List<ThemeSet> findAll() {
        Query q = entityManager.createQuery(
                "SELECT ts FROM ThemeSet ts", ThemeSet.class);
        return (List<ThemeSet>) q.getResultList();        
    }

    /**
     * finds entities in DB with given price
     *
     * @return List<ThemeSet> of all entities with given price
     */
    public List<ThemeSet> findByPrice(BigDecimal price) {
        Query q = entityManager.createQuery("SELECT ts FROM ThemeSet ts WHERE ts.price = :price", ThemeSet.class);
        q.setParameter("price", price);
        return (List<ThemeSet>) q.getResultList();
    }
    
}

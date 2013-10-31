package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.ThemeSet;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * DAO class for ThemeSet
 *
 * @author Pavol Bako
 */
@Repository
public class ThemeSetDaoImpl extends AbstractDao<ThemeSet> implements ThemeSetDao {

    /**
     * uses superclass constructor
     */
    public ThemeSetDaoImpl(){
        super();
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

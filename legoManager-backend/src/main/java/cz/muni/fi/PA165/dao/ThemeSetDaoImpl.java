package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

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
    
    public List<ThemeSet> findByCategory(Category category){
        if (category == null){
            throw new IllegalArgumentException("Category cannot be null.");
        }
        Query q = entityManager.createQuery("SELECT t FROM ThemeSet t " +
                "WHERE category = :category", ThemeSet.class);
        q.setParameter("category", category);
        return (List<ThemeSet>)q.getResultList();
    }
}


package cz.muni.fi.PA165.dao;

import static cz.muni.fi.PA165.dao.Dao.entityManager;
import cz.muni.fi.PA165.daoInterface.ThemeSetDaoInterface;
import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author PALO
 */
public class ThemeSetDAO extends Dao<ThemeSet> implements ThemeSetDaoInterface{

    public ThemeSetDAO(){
        super();
    }
    
    
    public void createOrUpdateThemeSet(ThemeSet set) {
        store(set);
    }

    public void removeThemeSet(ThemeSet set) {
        delete(set);
    }

    public List<ThemeSet> findAll() {
        Query q = entityManager.createQuery(
                "SELECT ts FROM ThemeSet ts", ThemeSet.class);
        return (List<ThemeSet>) q.getResultList();        
    }

    public List<ThemeSet> findByPrice(BigDecimal price) {
        Query q = entityManager.createQuery("SELECT ts FROM ThemeSet ts WHERE ts.price = :price", ThemeSet.class);
        q.setParameter("price", price);
        return (List<ThemeSet>) q.getResultList();
    }
    
}

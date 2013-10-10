package cz.muni.fi.PA165.daoInterface;

import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public interface ThemeSetDaoInterface {
    
    /**
     * creates and updates ThemeSet instance
     *
     * @param set instance of ThemeSet entity class, which has to be changed
     */
    public void createOrUpdateThemeSet(ThemeSet set);
    
    /**
     * removes the ThemeSet instance
     *
     * @param set instance of ThemeSet entity class
     */
    public void removeThemeSet(ThemeSet set);
    
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
    
}

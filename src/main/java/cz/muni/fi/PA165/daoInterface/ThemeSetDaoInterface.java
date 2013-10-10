package cz.muni.fi.PA165.daoInterface;

import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public interface ThemeSetDaoInterface {
    
    public void createOrUpdateThemeSet(ThemeSet set);
    
    public void removeThemeSet(ThemeSet set);
    
    public List<ThemeSet> findAll();
    
    public List<ThemeSet> findByPrice(BigDecimal price);
    
}

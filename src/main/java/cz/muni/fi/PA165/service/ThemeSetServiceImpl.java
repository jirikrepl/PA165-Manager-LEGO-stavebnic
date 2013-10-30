/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.ThemeSetDao;
import cz.muni.fi.PA165.entity.ThemeSet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author PALO
 */
public class ThemeSetServiceImpl implements ThemeSetService{
    
    private ThemeSetDao themeSetDao;

    public void setThemeSetDao(ThemeSetDao themeSetDao) {
        this.themeSetDao = themeSetDao;
    }

    public List<ThemeSet> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ThemeSet> findByPrice(BigDecimal price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(ThemeSet set) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void create(ThemeSet set) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ThemeSet findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

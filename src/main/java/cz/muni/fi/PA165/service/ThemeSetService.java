/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.domain.ThemeSet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author PALO
 */
public interface ThemeSetService {
    
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
}

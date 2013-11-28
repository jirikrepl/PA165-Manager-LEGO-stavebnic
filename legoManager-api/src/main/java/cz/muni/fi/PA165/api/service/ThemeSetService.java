package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public interface ThemeSetService {

    /**
     * finds all ThemeSet entities in DB
     *
     * @return List<ThemeSet> all entities in DB
     */
    public List<ThemeSetDto> findAll();

    /**
     * finds entities in DB with given price
     *
     * @return List<ThemeSet> of all entities with given price
     */
    public List<ThemeSetDto> findByPrice(BigDecimal price);

    /**
     * updates the ThemeSet in DB
     *
     * @param ThemeSet takes ThemeSet as parameter
     */
    public void update(ThemeSetDto setDto);

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
    public void create(ThemeSetDto setDto);

    /**
     * finds ThemeSet entity according to ID
     *
     * @param Long
     * @return ThemeSet
     */
    public ThemeSetDto findById(Long id);

    /**
     * retrieves theme sets with relation to given category
     *
     * @param categoryDto the given category
     */
    //public List<ThemeSetDto> findByCategory(CategoryDto categoryDto);
}

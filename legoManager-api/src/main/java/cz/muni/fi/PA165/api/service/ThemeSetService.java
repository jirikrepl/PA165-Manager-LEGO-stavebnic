package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * @author Pavol Bako
 */
public interface ThemeSetService {

    /**
     * finds all ThemeSet entities in DB
     *
     * @return List<ThemeSet> all entities in DB
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ThemeSetDto> findAll();

    /**
     * updates the ThemeSet in DB
     *
     * @param setDto takes ThemeSet as parameter
     */
    @Secured("ROLE_ADMIN")
    public void update(ThemeSetDto setDto);

    /**
     * deletes the ThemeSet in DB according to ID
     *
     * @param id
     */
    @Secured("ROLE_ADMIN")
    public void delete(Long id);

    /**
     * creates ThemeSet entity in DB
     *
     * @param setDto
     */
    @Secured("ROLE_ADMIN")
    public void create(ThemeSetDto setDto);

    /**
     * finds ThemeSet entity according to ID
     *
     * @param id
     * @return ThemeSet
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ThemeSetDto findById(Long id);

    /**
     * retrieves theme sets with relation to given category
     *
     * @param categoryDto the given category
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<ThemeSetDto> findByCategory(CategoryDto categoryDto);
}

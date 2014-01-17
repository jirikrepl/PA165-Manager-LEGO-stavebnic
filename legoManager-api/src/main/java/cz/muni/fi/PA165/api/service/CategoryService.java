package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
public interface CategoryService {

    /**
     * find all brick entities in db table
     *
     * @return List<Category> list of brick objects
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public List<CategoryDto> findAll();

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public CategoryDto findByName(String name);

    /**
     * updates given category
     *
     * @param category instance of Category entity class
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST" })
    public void update(CategoryDto category);

    /**
     * deletes given category
     *
     * @param id id of the instance of the category to remove
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST" })
    public void delete(Long id);

    /**
     * persist given category
     *
     * @param category instance of Category entity class
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST" })
    public void create(CategoryDto category);

    /**
     * retrieves category with given id from the database
     *
     * @param id the given id
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public CategoryDto findById(Long id);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.entity.BuildingKit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Interface for BuildingKit service
 * 
 * @author Tomas Kopecky
 */
public interface BuildingKitService {
    /**
     * persist given building kit
     *
     * @param buildingKit instance of BuildingKit entity class
     */
    public void create(BuildingKit buildingKit);

    /**
     * deletes given building kit
     *
     * @param id id of the instance of the building kit to remove
     */
    public void delete(Long id);

    /**
     * updates given building kit
     *
     * @param buildingKit instance of BuildingKit entity class
     */
    public void update(BuildingKit buildingKit);

    /**
     * sets the entity manager
     *
     * @param entityManager instance of EntityManager class
     */
    public void setEntityManager(EntityManager entityManager);

    /**
     * retrieves all building kits from the database
     *
     */
    public List<BuildingKit> findAll();

    /**
     * retrieves all building kits with given price from the database
     *
     * @param price the given price
     */
    public List<BuildingKit> findByPrice(BigDecimal price);

    /**
     * retrieves all building kits with given year or higher year from the database
     *
     * @param yearFrom the given year
     */
    public List<BuildingKit> findByYearFrom(int yearFrom);

    /**
     * retrieves building kit with given id from the database
     *
     * @param id the given id
     */
    public BuildingKit findById(Long id);
}

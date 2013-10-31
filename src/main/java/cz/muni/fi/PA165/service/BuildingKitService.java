/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for BuildingKit service
 * 
 * @author Tomas Kopecky
 */
public interface BuildingKitService {
    
    /**
     * creates given building kit
     *
     * @param buildingKit instance of BuildingKit DTO class
     */
    public void create(BuildingKitDto buildingKit);

    /**
     * deletes given building kit
     *
     * @param id id of the instance of the building kit to remove
     */
    public void delete(Long id);

    /**
     * updates given building kit
     *
     * @param buildingKit instance of BuildingKit DTO class
     */
    public void update(BuildingKitDto buildingKit);
    
    /**
     * retrieves all building kits
     *
     */
    public List<BuildingKitDto> findAll();

    /**
     * retrieves all building kits with given price
     *
     * @param price the given price
     */
    public List<BuildingKitDto> findByPrice(BigDecimal price);

    /**
     * retrieves all building kits with given year or higher year
     *
     * @param yearFrom the given year
     */
    public List<BuildingKitDto> findByYearFrom(int yearFrom);

    /**
     * retrieves building kit with given id
     *
     * @param id the given id
     */
    public BuildingKitDto findById(Long id);
    
    /**
     * set buildingkit dao object
     * @param buildingKitDao dao object for building kit entity
     */
    public void setBuildingKitDao(BuildingKitDao buildingKitDao);
}

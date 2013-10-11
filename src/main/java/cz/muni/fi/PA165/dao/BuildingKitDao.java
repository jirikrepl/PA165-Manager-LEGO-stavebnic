/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.BuildingKit;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tomas Kopecky
 */
public interface BuildingKitDao {
    public void create(BuildingKit buildingKit);

    public void delete(BuildingKit buildingKit);

    public void update(BuildingKit buildingKit);

    public void setEntityManager(EntityManager entityManager);

    public List<BuildingKit> findAll();

    public List<BuildingKit> findByPrice(BigDecimal price);

    public List<BuildingKit> findByYearFrom(int yearFrom);

    public BuildingKit findById(Long id);

}

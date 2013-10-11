/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.BuildingKit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Tomas Kopecky
 */
public interface BuildingKitDao {
    public void CreateBuildingKit(BuildingKit buildingKit);
    public void RemoveBuildingKit(BuildingKit buildingKit);
    public void UpdateBuildingKit(BuildingKit buildingKit);
    
    public void setEntityManager(EntityManager entityManager);
    
    public List<BuildingKit> findAll();
    public List<BuildingKit> findByPrice(BigDecimal price);
    public List<BuildingKit> findByYearFrom(int yearFrom);
}

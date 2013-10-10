/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.BuildingKit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author pc
 */
public class BuildingKitDao extends Dao<BuildingKit> {
    
//    public void CreateBuildingKit(BuildingKit buildingKit)
//    {
//       persist(buildingKit);
//    }
//
//    public void RemoveBuildingKit(BuildingKit buildingKit) {
//        remove(buildingKit);
//    }
//
//    public void UpdateBuildingKit(BuildingKit buildingKit)
//    {
//       persist(buildingKit);
//    }
    
    public List<BuildingKit> findAll() {
        Query q = entityManager.createQuery(
                "SELECT b FROM BuildingKit b", Brick.class);
        return (List<BuildingKit>) q.getResultList();
    }

    public List<BuildingKit> findByPrice(BigDecimal price) {
        Query q = entityManager.createQuery("SELECT b FROM " + 
                "BuildingKit b WHERE price <= :price",
                BuildingKit.class);
        q.setParameter("price", price);
        return (List<BuildingKit>) q.getResultList();
    }

    public List<BuildingKit> findByYearFrom(int yearFrom) {
        Query q = entityManager.createQuery("SELECT b FROM " + 
                "BuildingKit b WHERE yearFrom >= :yearFrom",
                BuildingKit.class);
        q.setParameter("yearFrom", yearFrom);
        return (List<BuildingKit>) q.getResultList();
    }
}

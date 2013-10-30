/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Tomas Kopecky
 */
public class BuildingKitServiceImpl implements BuildingKitService {

    private BuildingKitDao buildingKitDao;

    public void setBuildingKitDao(BuildingKitDao dao) {
        buildingKitDao = dao;
    }
    
    public void create(BuildingKit buildingKit) {
        buildingKitDao.create(buildingKit);
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(BuildingKit buildingKit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEntityManager(EntityManager entityManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKit> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKit> findByPrice(BigDecimal price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKit> findByYearFrom(int yearFrom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BuildingKit findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

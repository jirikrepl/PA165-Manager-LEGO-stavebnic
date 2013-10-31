/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.dto.BuildingKitDto;
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
    
    public void create(BuildingKitDto buildingKit) {
        buildingKitDao.create(buildingKit.createEntity());
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(BuildingKitDto buildingKit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEntityManager(EntityManager entityManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKitDto> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKitDto> findByPrice(BigDecimal price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BuildingKitDto> findByYearFrom(int yearFrom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BuildingKitDto findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

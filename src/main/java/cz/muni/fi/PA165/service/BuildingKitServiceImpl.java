/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Kopecky
 */
@Transactional
@Service
public class BuildingKitServiceImpl implements BuildingKitService {

    //@Autowired
    private BuildingKitDao buildingKitDao;

    public void setBuildingKitDao(BuildingKitDao dao) {
        buildingKitDao = dao;
    }
    
    public void create(BuildingKitDto buildingKit) {
        buildingKitDao.create(buildingKit.createEntity());
    }

    public void delete(Long id) {
        buildingKitDao.delete(id);
    }

    public void update(BuildingKitDto buildingKit) {
        buildingKitDao.update(buildingKit.createEntity());
    }

    public List<BuildingKitDto> findAll() {
        List<BuildingKit> buildingKitEntities = buildingKitDao.findAll();
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        for (BuildingKit bk : buildingKitEntities) {
            buildingKitDtoList.add(BuildingKitConversion.convertToDto(bk));
        }
        return buildingKitDtoList;
    }

    public List<BuildingKitDto> findByPrice(BigDecimal price) {
        List<BuildingKit> buildingKitEntities = buildingKitDao.findByPrice(price);
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        for (BuildingKit bk : buildingKitEntities) {
            buildingKitDtoList.add(BuildingKitConversion.convertToDto(bk));
        }
        return buildingKitDtoList;
    }

    public List<BuildingKitDto> findByYearFrom(int yearFrom) {
        List<BuildingKit> buildingKitEntities = buildingKitDao.findByYearFrom(yearFrom);
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        for (BuildingKit bk : buildingKitEntities) {
            buildingKitDtoList.add(BuildingKitConversion.convertToDto(bk));
        }
        return buildingKitDtoList;
    }

    public BuildingKitDto findById(Long id) {
        BuildingKit entity = buildingKitDao.findById(id);
        return BuildingKitConversion.convertToDto(entity);
    }
    
}

package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.entity.BuildingKit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Tomas Kopecky
 */
@Transactional
@Service
public class BuildingKitServiceImpl implements BuildingKitService {

    @Autowired
    private BuildingKitDao buildingKitDao;

    public void setBuildingKitDao(BuildingKitDao dao) {
        buildingKitDao = dao;
    }
    
    public void create(BuildingKitDto buildingKit) {
        if (buildingKit == null) {
            throw new DataAccessException("Building kit cannot be null.") {};
        }
        buildingKitDao.create(BuildingKitConversion.convertToEntity(buildingKit));
    }

    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessException("Id of the building kit cannot be null.") {};
        }
        buildingKitDao.delete(id);
    }

    public void update(BuildingKitDto buildingKit) {
        if (buildingKit == null) {
            throw new DataAccessException("Building kit cannot be null.") {};
        }
        buildingKitDao.update(BuildingKitConversion.convertToEntity(buildingKit));
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
        if (price == null){
            throw new DataAccessException("Price cannot be null.") {};
        }
        List<BuildingKit> buildingKitEntities = buildingKitDao.findByPrice(price);
        List<BuildingKitDto> buildingKitDtoList = new ArrayList<BuildingKitDto>();
        for (BuildingKit bk : buildingKitEntities) {
            BuildingKitDto kitDto = BuildingKitConversion.convertToDto(bk);
            buildingKitDtoList.add(kitDto);
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
        if (id == null) {
            throw new DataAccessExceptionService("Id of the building kit cannot be null.");
        }
        BuildingKit entity = buildingKitDao.findById(id);
        return BuildingKitConversion.convertToDto(entity);
    }

    public List<BuildingKitDto> findByCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new DataAccessExceptionService("Category cannot be null.");
        }
        Category category = CategoryConversion.convertToEntity(categoryDto);
        List<BuildingKit> kits = buildingKitDao.findByCategory(category);
        List<BuildingKitDto> results = new ArrayList<BuildingKitDto>();
        for (BuildingKit kit : kits) {
            results.add(BuildingKitConversion.convertToDto(kit));
        }
        return results;
    }

    public List<BuildingKitDto> findByThemeSet(ThemeSetDto themeSetDto) {
        if (themeSetDto == null) {
            throw new DataAccessExceptionService("Theme set cannot be null.");
        }
        ThemeSet themeSet = ThemeSetConversion.convertToEntity(themeSetDto);
        List<BuildingKit> kits = buildingKitDao.findByThemeSet(themeSet);
        List<BuildingKitDto> results = new ArrayList<BuildingKitDto>();
        for (BuildingKit kit : kits) {
            results.add(BuildingKitConversion.convertToDto(kit));
        }
        return results;
    }
}

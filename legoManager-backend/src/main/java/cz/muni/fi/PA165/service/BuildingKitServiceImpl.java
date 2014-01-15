package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.BrickDto;
import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.BuildingKitService;
import cz.muni.fi.PA165.dao.BuildingKitDao;
import cz.muni.fi.PA165.daoDtoConversion.BrickConversion;
import cz.muni.fi.PA165.daoDtoConversion.BuildingKitConversion;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.annotation.Secured;

import java.util.ArrayList;
import java.util.List;

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

    @Secured("ROLE_ADMIN")
    public void create(BuildingKitDto buildingKit) {
        if (buildingKit == null) {
            throw new DataAccessException("Building kit cannot be null.") {};
        }
        buildingKitDao.create(BuildingKitConversion.convertToEntity(buildingKit));
    }

    @Secured("ROLE_ADMIN")
    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessException("Id of the building kit cannot be null.") {};
        }
        buildingKitDao.delete(id);
    }

    @Secured("ROLE_ADMIN")
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
        List<BuildingKit> kits;
        try {
            Category category = CategoryConversion.convertToEntity(categoryDto);
            kits = buildingKitDao.findByCategory(category);
        }
        catch (DataAccessException e) {
            return new ArrayList<BuildingKitDto>();
        }
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

    public List<BuildingKitDto> findByBrick(BrickDto brickDto) {
        if (brickDto == null) {
            throw new DataAccessExceptionService("Theme set cannot be null.");
        }
        
        Brick brick = BrickConversion.convertToEntity(brickDto);
        List<BuildingKit> kits = buildingKitDao.findByBrick(brick);
        List<BuildingKitDto> result = new ArrayList<BuildingKitDto>();
        for(BuildingKit kit : kits) {
            result.add(BuildingKitConversion.convertToDto(kit));
        }
        return result;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.dao.DaoException;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;
import org.hibernate.exception.DataException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Kopecky
 */
public class BuildingKitConversion {
    /**
     * creates DTO object from this entity
     * @param buildingKit buildingKit entity
     * @return BuildingKitDto dto object
     */
    public static BuildingKitDto convertToDto(BuildingKit buildingKit) {
        if (buildingKit == null) {

            throw new DaoException("Entity can not be NULL");
        }
        BuildingKitDto buildingKitDto = new BuildingKitDto();
        buildingKitDto.setId(buildingKit.getId());
        buildingKitDto.setName(buildingKit.getName());
        buildingKitDto.setYearFrom(buildingKit.getYearFrom());
        buildingKitDto.setPrice(buildingKit.getPrice());
        buildingKitDto.setDescription(buildingKit.getDescription());
        buildingKitDto.setThemeSet(ThemeSetConversion.convertToDto(buildingKit.getThemeSet()));
        
        List<BrickDto> bricksDto = new ArrayList<BrickDto>();
        List<Brick> bricks = buildingKit.getBricks();
        for (Brick brick : bricks) {
            bricksDto.add(BrickConversion.convertToDto(brick));
        }
        buildingKitDto.setBricks(bricksDto);
        return buildingKitDto;
    }
    
    /**
     * creates entity BuildingKit from this dto object
     * @param buildingKitDto brick DTO object
     * @return BuildingKit entity
     */
    public static BuildingKit convertToEntity(BuildingKitDto buildingKitDto) {
        if (buildingKitDto == null) {
            throw new IllegalArgumentException("Dto can not be NULL");
        }
        BuildingKit kit = new BuildingKit();
        kit.setId(buildingKitDto.getId());
        kit.setDescription(buildingKitDto.getDescription());
        kit.setName(buildingKitDto.getName());
        kit.setYearFrom(buildingKitDto.getYearFrom());
        kit.setPrice(buildingKitDto.getPrice());
        kit.setThemeSet(ThemeSetConversion.convertToEntity(buildingKitDto.getThemeSet()));
        
        List<Brick> brickEntities = new ArrayList<Brick>();
        List<BrickDto> bricks = buildingKitDto.getBricks();
        for (BrickDto br : bricks) {
            brickEntities.add(BrickConversion.convertToEntity(br));
        }
        kit.setBricks(brickEntities);
        
        return kit;
    }
}

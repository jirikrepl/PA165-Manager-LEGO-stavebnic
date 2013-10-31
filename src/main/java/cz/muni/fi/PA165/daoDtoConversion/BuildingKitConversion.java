/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.dto.BuildingKitDto;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.BuildingKit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pcz
 */
public class BuildingKitConversion {
    /**
     * creates DTO object from this entity
     * @return BuildingKitDto dto object
     */
    public static BuildingKitDto convertToDto(BuildingKit buildingKit) {
        if (buildingKit == null) {
            throw new IllegalArgumentException("Entity can not be NULL");
        }
        BuildingKitDto buildingKitDto = new BuildingKitDto();
        buildingKitDto.setId(buildingKit.getId());
        buildingKitDto.setName(buildingKit.getName());
        buildingKitDto.setYearFrom(buildingKit.getYearFrom());
        buildingKitDto.setPrice(buildingKit.getPrice());
        buildingKitDto.setDescription(buildingKit.getDescription());
        buildingKitDto.setThemeSet(buildingKit.getThemeSet().createDto());
        
        List<BrickDto> bricksDto = new ArrayList<BrickDto>();
        List<Brick> bricks = buildingKit.getBricks();
        for (Brick brick : bricks) {
            bricksDto.add(BrickConversion.convertToDto(brick));
        }
        buildingKitDto.setBricks(bricksDto);
        return buildingKitDto;
    }
}

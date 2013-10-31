/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.ThemeSet;

/**
 *
 * @author Pavol Bako
 */


public class ThemeSetConversion {
    
    /**
     * creates dto ThemeSetDto from this entity object
     * @return ThemeSetDto entity
     */
    public static ThemeSetDto convertToDto(ThemeSet themeSet) {
        if (themeSet == null) {
            throw new IllegalArgumentException("Entity can not be NULL");
        }
        ThemeSetDto dto = new ThemeSetDto();
        dto.setId(themeSet.getId());
        dto.setName(themeSet.getName());
        dto.setPrice(themeSet.getPrice());
        dto.setCategory(themeSet.getCategory());
        dto.setDescription(themeSet.getDescription());
        dto.setBuildingKits(themeSet.getBuildingKits());
        return dto;
    }
    
    /**
     * creates entity ThemeSet from this dto object
     * @return ThemeSet entity
     */
    public static ThemeSet convertToEntity(ThemeSetDto themeSetDto) {
        if (themeSetDto == null) {
            throw new IllegalArgumentException("Dto can not be NULL");
        }
        ThemeSet themeSet = new ThemeSet();
        
        themeSet.setId(themeSetDto.getId());
        themeSet.setDescription(themeSetDto.getDescription());
        themeSet.setName(themeSetDto.getName());
        themeSet.setCategory(themeSetDto.getCategory());
        themeSet.setPrice(themeSetDto.getPrice());
        themeSet.setBuildingKits(themeSetDto.getBuildingKits());
        return themeSet;
    }
}

package cz.muni.fi.PA165.conflictDto;

import cz.muni.fi.PA165.api.dto.BuildingKitDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;

import java.util.List;

/**
 * @author: Martin Rumanek
 * @version: 12/12/13
 */
public class CategoryConflictDto {
    List<BuildingKitDto> buildingKitDtoList;
    List <ThemeSetDto> themeSetDtoList;

    public CategoryConflictDto() {
    }

    public List<BuildingKitDto> getBuildingKitDtoList() {
        return buildingKitDtoList;
    }

    public void setBuildingKitDtoList(List<BuildingKitDto> buildingKitDtoList) {
        this.buildingKitDtoList = buildingKitDtoList;
    }

    public List<ThemeSetDto> getThemeSetDtoList() {
        return themeSetDtoList;
    }

    public void setThemeSetDtoList(List<ThemeSetDto> themeSetDtoList) {
        this.themeSetDtoList = themeSetDtoList;
    }
}

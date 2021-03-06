package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;

/**
 * @author Pavol Bako
 */

public class ThemeSetConversion {

    /**
     * creates dto ThemeSetDto from this entity object
     *
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

        Category category = themeSet.getCategory();

        if (category != null) {
            CategoryDto categoryDto = CategoryConversion.conversionToDto(category);
            dto.setCategoryDto(categoryDto);
        }

        dto.setDescription(themeSet.getDescription());

        return dto;
    }

    /**
     * creates entity ThemeSet from this dto object
     *
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

        // convesion of category dto to category entity
        CategoryDto categoryDto = themeSetDto.getCategoryDto();
        if (categoryDto != null) {
            Category category = CategoryConversion.convertToEntity(categoryDto);
            themeSet.setCategory(category);
        }

        themeSet.setPrice(themeSetDto.getPrice());
        return themeSet;
    }
}

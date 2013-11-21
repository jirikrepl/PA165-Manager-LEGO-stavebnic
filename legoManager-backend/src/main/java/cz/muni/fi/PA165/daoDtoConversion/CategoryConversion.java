/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.entity.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PALO
 */
public class CategoryConversion {
    public static CategoryDto conversionToDto(Category category){
        if (category == null){
            throw new IllegalArgumentException("Entity cannost be NULL");
            
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        
        return categoryDto;
    }
    
    public static Category convertToEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            throw new IllegalArgumentException("Entity cannost be NULL");            
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        
        return category;
    }
}
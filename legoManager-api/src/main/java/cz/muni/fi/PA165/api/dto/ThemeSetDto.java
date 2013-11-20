package cz.muni.fi.PA165.api.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
public class ThemeSetDto {
    
    private Long id;
    private BigDecimal price;
    private String description;
    private String name;
    
    private CategoryDto categoryDto;
    
    private List<BuildingKitDto> buildingKitsDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto category) {
        this.categoryDto = category;
    }

    public List<BuildingKitDto> getBuildingKitsDto() {
        return buildingKitsDto;
    }

    public void setBuildingKitsDto(List<BuildingKitDto> buildingKits) {
        this.buildingKitsDto = buildingKits;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThemeSetDto other = (ThemeSetDto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ThemeSetDto{" + "id=" + id + ", price=" + price + ", description=" + description + ", name=" + name + '}';
    }
    
}
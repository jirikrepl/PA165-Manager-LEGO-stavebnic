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


    //TODO proc to teda zustalo v DAO
    /*
    public List<BuildingKitDto> getBuildingKitsDto() {
        return buildingKitsDto;
    }

    public void setBuildingKitsDto(List<BuildingKitDto> buildingKits) {
        this.buildingKitsDto = buildingKits;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThemeSetDto)) return false;

        ThemeSetDto that = (ThemeSetDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThemeSetDto{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", categoryDto=" + categoryDto +
                ", buildingKitsDto=" + buildingKitsDto +
                '}';
    }
}
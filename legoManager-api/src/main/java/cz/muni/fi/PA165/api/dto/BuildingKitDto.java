package cz.muni.fi.PA165.api.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Tomas Kopecky
 */
public class BuildingKitDto {
    private Long id;
    private String name;
    private int yearFrom;
    private BigDecimal price;
    private String description;
    private Map<BrickDto, Integer> bricks;
    private ThemeSetDto themeSet;
    private CategoryDto category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
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

    public Map<BrickDto, Integer> getBricks() {
        return bricks;
    }

    public void setBricks(Map<BrickDto, Integer> bricks) {
        this.bricks = bricks;
    }

    public ThemeSetDto getThemeSet() {
        return themeSet;
    }

    public void setThemeSet(ThemeSetDto themeSet) {
        this.themeSet = themeSet;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuildingKitDto)) return false;

        BuildingKitDto that = (BuildingKitDto) o;

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
        return "BuildingKitDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearFrom=" + yearFrom +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", bricks=" + bricks +
                ", themeSet=" + themeSet +
                ", category=" + category +
                '}';
    }
}

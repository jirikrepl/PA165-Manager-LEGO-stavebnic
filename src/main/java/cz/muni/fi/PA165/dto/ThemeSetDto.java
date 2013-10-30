/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dto;

import cz.muni.fi.PA165.entity.BuildingKit;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author PALO
 */
public class ThemeSetDto {
    
    private Long id;
    private BigDecimal price;
    private String description;
    private String name;
    
    private Category category;
    
    private List<BuildingKit> buildingKits;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BuildingKit> getBuildingKits() {
        return buildingKits;
    }

    public void setBuildingKits(List<BuildingKit> buildingKits) {
        this.buildingKits = buildingKits;
    }
    
    
    
    public ThemeSet createEntity(){
        ThemeSet ts = new ThemeSet();
        ts.setName(name);
        ts.setPrice(price);
        ts.setDescription(description);
        ts.setCategory(category);
        ts.setBuildingKits(buildingKits);
        return ts;
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

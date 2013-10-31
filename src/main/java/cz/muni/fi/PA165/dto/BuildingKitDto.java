/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dto;

import cz.muni.fi.PA165.entity.BuildingKit;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Tomas Kopecky
 */
public class BuildingKitDto {
    private Long id;
    private String name;
    private int yearFrom;
    private BigDecimal price;
    private String description;
    private List<BrickDto> bricks;
    private ThemeSetDto themeSet;

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

    public List<BrickDto> getBricks() {
        return bricks;
    }

    public void setBricks(List<BrickDto> bricks) {
        this.bricks = bricks;
    }

    public ThemeSetDto getThemeSet() {
        return themeSet;
    }

    public void setThemeSet(ThemeSetDto themeSet) {
        this.themeSet = themeSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + this.yearFrom;
        hash = 29 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 29 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 29 * hash + (this.bricks != null ? this.bricks.hashCode() : 0);
        hash = 29 * hash + (this.themeSet != null ? this.themeSet.hashCode() : 0);
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
        final BuildingKitDto other = (BuildingKitDto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.yearFrom != other.yearFrom) {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.bricks != other.bricks && (this.bricks == null || !this.bricks.equals(other.bricks))) {
            return false;
        }
        if (this.themeSet != other.themeSet && (this.themeSet == null || !this.themeSet.equals(other.themeSet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BuildingKitDto{" + "id=" + id + ", name=" + name + ", yearFrom=" + yearFrom + ", price=" + price + ", description=" + description + ", bricks=" + bricks + ", themeSet=" + themeSet + '}';
    }
    
    public BuildingKit createEntity() {
        BuildingKit kit = new BuildingKit();
        kit.setId(this.getId());
        kit.setDescription(this.getDescription());
        kit.setName(this.getName());
        kit.setYearFrom(this.getYearFrom());
        kit.setPrice(this.getPrice());
        //kit.setThemeSet(this.getThemeSet().createEntity());
        
        /*
        List<Brick> brickEntities = new ArrayList<Brick>();
        List<BrickDto> bricks = this.getBricks();
        for (BrickDto br : bricks) {
            brickEntities.add(br.createEntity());
        }
        */
        return kit;
    }
}

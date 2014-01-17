package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Tomas Kopecky
 */
@Entity
public class BuildingKit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BuildingKitSequence")
    @SequenceGenerator(name = "BuildingKitSequence", sequenceName = "BUILDINGKIT_SEQ", initialValue=150, allocationSize=1)
    private Long id;
    private String name;
    private int yearFrom;
    private BigDecimal price;
    private String description;
    @ElementCollection
    private java.util.Map<Brick, Integer> bricks;
    @ManyToOne
    private ThemeSet themeSet;
    @ManyToOne
    private Category category;

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

    public Map<Brick, Integer> getBricks() {
        return bricks;
    }

    public void setBricks(Map<Brick, Integer> bricks) {
        this.bricks = bricks;
    }

    public ThemeSet getThemeSet() {
        return themeSet;
    }

    public void setThemeSet(ThemeSet themeSet) {
        this.themeSet = themeSet;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BuildingKit{" +
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

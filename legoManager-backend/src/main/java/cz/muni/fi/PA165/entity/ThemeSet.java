package cz.muni.fi.PA165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
@Entity
public class ThemeSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private String description;
    private String name;
    
    @ManyToOne
    private Category category;
    
    @OneToMany(mappedBy ="themeSet")
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

    @Override
    public String toString() {
        return "ThemeSet{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", buildingKits=" + buildingKits +
                '}';
    }
}

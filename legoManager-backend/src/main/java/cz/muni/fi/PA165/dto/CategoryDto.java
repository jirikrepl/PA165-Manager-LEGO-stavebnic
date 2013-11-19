package cz.muni.fi.PA165.dto;

import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.api.service.Color;

/**
 * @author: Martin Rumanek
 * @version: 10/30/13
 */
public class CategoryDto {

    private Long id;
    private Color color;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryDto that = (CategoryDto) o;

        if (color != that.color) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        //if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Category createEntity() {
        Category category = new Category();
        category.setId(this.getId());
        category.setDescription(this.getDescription());
        category.setName(this.getName());

        return category;
    }
}

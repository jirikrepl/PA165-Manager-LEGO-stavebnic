package cz.muni.fi.PA165.api.dto;

import cz.muni.fi.PA165.api.service.Color;

/**
 * this class represent Dto object of Brick entity
 * @author Jiri Krepl
 */
public class BrickDto {

    private Long id;
    private Color color;
    private String name;
    private String description;

    public BrickDto( String name, Color color, String description) {
        this.color = color;
        this.name = name;
        this.description = description;
    }

    public BrickDto() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrickDto)) return false;

        BrickDto brickDto = (BrickDto) o;

        if (color != brickDto.color) return false;
        if (id != null ? !id.equals(brickDto.id) : brickDto.id != null) return false;
        if (name != null ? !name.equals(brickDto.name) : brickDto.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BrickDto{" +
                "id=" + id +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

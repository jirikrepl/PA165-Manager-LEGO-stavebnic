package cz.muni.fi.PA165.entity;

import cz.muni.fi.PA165.api.service.Color;

import javax.persistence.*;
import java.io.Serializable;

/**
 * this entity class represents lego brick
 *
 * @author Jiri Krepl
 */
@Entity
public class Brick implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BrickSequence")
    @SequenceGenerator(name = "BrickSequence", sequenceName = "BRICK_SEQ", initialValue = 150)
    private Long id;
    private Color color;
    private String name;
    private String description;

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
    public String toString() {
        return "Brick{" +
                "id=" + id +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
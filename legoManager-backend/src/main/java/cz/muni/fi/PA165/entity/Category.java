package cz.muni.fi.PA165.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Martin Rumanek
 * @version: 10/8/13
 */
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CategorySequence")
    @SequenceGenerator(name = "CategorySequence", sequenceName = "CATEGORY_SEQ", initialValue = 150)
    private Long id;
    @Column(unique=true)
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

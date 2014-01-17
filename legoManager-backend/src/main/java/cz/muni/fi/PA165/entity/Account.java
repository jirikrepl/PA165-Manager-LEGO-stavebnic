package cz.muni.fi.PA165.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pavol Bako on 6.1.14.
 */

@Entity
public class Account implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AccountSequence")
    @SequenceGenerator(name = "AccountSequence", sequenceName = "ACCOUNT_SEQ", initialValue=150)
    private Long id;
    @Column(nullable=false,unique=true)
    private String name;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isAdmin;


    //Getters and Setters
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

}

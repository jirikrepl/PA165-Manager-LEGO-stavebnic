/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Jiri Krepl
 */
public interface BrickDao {

    /**
     * persist given brick entity
     *
     * @param brick instance of brick entity class
     */
    public void create(Brick brick);

    public void update(Brick brick);

    /**
     * remove brick entity from table
     *
     * @param brick id of brick entity class, which has to be removed
     */
    public void delete(Long id);

    /**
     * find all brick entities in db table
     *
     * @return List<Brick> list of brick objects
     */
    public List<Brick> findAll();

    public Brick findById(Long id);

    /**
     * find all bricks of desired color
     *
     * @param color Enum color of bricks
     * @return List<Brick> list of brick with desired color
     */
    public List<Brick> findByColor(Color color);

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    public List<Brick> findByName(String name);

    public void setEntityManager(EntityManager entityManager);
}

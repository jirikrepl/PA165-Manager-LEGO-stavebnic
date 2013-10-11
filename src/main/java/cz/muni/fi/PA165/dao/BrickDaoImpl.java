package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;

import javax.persistence.Query;
import java.util.List;

/**
 * DAO class for brick
 *
 * @author jirikrepl
 */
public class BrickDaoImpl extends AbstractDao<Brick> implements BrickDao {

    /**
     * uses superclass constructor
     */
    public BrickDaoImpl() {
        super();
    }

    /**
     * find all brick entities in db table
     *
     * @return List<Brick> list of brick objects
     */
    public List<Brick> findAll() {
        Query q = entityManager.createQuery(
                "SELECT b FROM Brick b", Brick.class);
        return (List<Brick>) q.getResultList();
    }

    /**
     * find all bricks of desired color
     *
     * @param color Enum color of bricks
     * @return List<Brick> list of brick with desired color
     */
    public List<Brick> findByColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can not be null");
        }
        Query q = entityManager.createQuery(
                "SELECT b FROM Brick b WHERE color = :brick_color", Brick.class);
        q.setParameter("brick_color", color);
        return (List<Brick>) q.getResultList();
    }

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    public List<Brick> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }

        Query q = entityManager.createQuery(
                "SELECT b FROM Brick b WHERE name = :brick_color", Brick.class);
        q.setParameter("brick_color", name);
        return (List<Brick>) q.getResultList();
    }
}
package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;
import cz.muni.fi.PA165.daoInterface.BrickDaoInterface;
import java.util.List;
import javax.persistence.Query;

/**
 * DAO class for brick
 *
 * @author Jiri Krepl
 */
public class BrickDao extends Dao<Brick> implements BrickDaoInterface{

    /**
     * uses superclass constructor
     */
    public BrickDao() {
        super();
    }

    /**
     * persist given brick entity
     *
     * @param brick instance of brick entity class
     */
    public void storeBrick(Brick brick) {
        store(brick);
    }

    /**
     * remove brick entity from table
     *
     * @param brick instance of brick entity class, which has to be removed
     */
    public void removeBrick(Brick brick) {
        delete(brick);
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
        // MUZOU MIT DVE KOSTICKY STEJNE JMENO?
        Query q = entityManager.createQuery(
                "SELECT b FROM Brick b WHERE name = :brick_color", Brick.class);
        q.setParameter("brick_color", name);
        return (List<Brick>) q.getResultList();
    }
}
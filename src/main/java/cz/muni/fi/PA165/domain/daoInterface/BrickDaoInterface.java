
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.domain.daoInterface;

import cz.muni.fi.PA165.domain.Brick;
import cz.muni.fi.PA165.domain.Color;
import java.util.List;

/**
 *
 * @author jirikrepl
 */
public interface BrickDaoInterface {

    public void storeBrick(Brick brick);

    /**
     * remove brick entity from table
     *
     * @param brick instance of brick entity class, which has to be removed
     */
    public void removeBrick(Brick brick);

    /**
     * find all brick entities in db table
     *
     * @return List<Brick> list of brick objects
     */
    public List<Brick> findAll();

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
}

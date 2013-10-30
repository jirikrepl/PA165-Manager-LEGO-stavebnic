/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.entity.Color;
import java.util.List;

/**
 *
 * @author Jiri
 */
public interface BrickService {
    /**
     * persist given brick entity
     *
     * @param brick instance of brick entity class
     */
    public void create(BrickDto brick);

    /**
     * updates instance of brick entity
     * @param brick instance of brick entity
     */
    public void update(BrickDto brick);

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
    public List<BrickDto> findAll();

    /**
     * 
     * @param id finds entity by given id
     * @return instance of Brick entity
     */
    public BrickDto findById(Long id);

    /**
     * find all bricks of desired color
     *
     * @param color Enum color of bricks
     * @return List<Brick> list of brick with desired color
     */
    public List<BrickDto> findByColor(Color color);

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    public List<BrickDto> findByName(String name);
    
    /**
     * set brick dao object
     * @param brickDao dao object for brick entity
     */
    public void setBrickDao(BrickDao brickDao);
}

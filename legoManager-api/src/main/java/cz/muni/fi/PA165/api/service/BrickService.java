package cz.muni.fi.PA165.api.service;



import cz.muni.fi.PA165.api.dto.BrickDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * this is interface of Brick class on service layer
 * 
 * @author Jiri Krepl
 */
public interface BrickService {
    /**
     * persist given brick entity
     *
     * @param brick instance of brick entity class
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    public void create(BrickDto brick);

    /**
     * updates instance of brick entity
     * @param brick instance of brick entity
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    public void update(BrickDto brick);

    /**
     * remove brick entity from table
     *
     * @param id id of brick entity class, which has to be removed
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    public void delete(Long id);

    /**
     * find all brick entities in db table
     *
     * @return List<Brick> list of brick objects
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public List<BrickDto> findAll();

    /**
     * 
     * @param id finds entity by given id
     * @return instance of Brick entity
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public BrickDto findById(Long id);

    /**
     * find all bricks of desired color
     *
     * @param color Enum color of bricks
     * @return List<Brick> list of brick with desired color
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public List<BrickDto> findByColor(Color color);

    /**
     * find all entities by given color name
     *
     * @param name String, name of brick
     * @return List<Brick> list of brick with desired color
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_REST" })
    public List<BrickDto> findByName(String name);

}

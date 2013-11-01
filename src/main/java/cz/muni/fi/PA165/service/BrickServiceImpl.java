package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.dao.BrickDao;
import cz.muni.fi.PA165.daoDtoConversion.BrickConversion;
import cz.muni.fi.PA165.dto.BrickDto;
import cz.muni.fi.PA165.entity.Brick;
import cz.muni.fi.PA165.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * this class contains operation with Brick Dto object on service layer
 * 
 * @author Jiri Krepl
 */
@Service
@Transactional
public class BrickServiceImpl implements BrickService {

    @Autowired
    private BrickDao brickDao;

    public void setBrickDao(BrickDao brickDao) {
        this.brickDao = brickDao;
    }

    /**
     * creates a new brick
     * @param brickDto Dto object of Brick
     */
    public void create(BrickDto brickDto) {
        if(brickDto == null) {
            throw new DataAccessExceptionService("created brick cannot be null");
        }
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.create(brickEntity);
    }

    /**
     * updates a brick
     * @param brickDto Dto object of Brick
     */
    public void update(BrickDto brickDto) {
        if(brickDto == null) {
            throw new DataAccessExceptionService("updated brick cannot be null");
        }
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.update(brickEntity);
    }
    
    /**
     * updates a brick by its id
     * @param id id of brick
     */
    public void delete(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("cannot delete brick with null id");
        }
        brickDao.delete(id);
    }

    /**
     * find all brick in system
     * @return list of bricks
     */
    public List<BrickDto> findAll() {
        List<Brick> brickEntitites = brickDao.findAll();
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }

    /**
     * find one brick by its id
     * @param id id of the brick
     * @return brick with desired id
     */
    public BrickDto findById(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("cannot find brick with null id");
        }
        Brick entity = brickDao.findById(id);
        return BrickConversion.convertToDto(entity);
    }

    /**
     * finds brick by its color
     * @param color color of the brick
     * @return brick with desired color
     */
    public List<BrickDto> findByColor(Color color) {
        if(color == null) {
            throw new DataAccessExceptionService("cannot find brick with null color");
        }
        List<Brick> brickEntitites = brickDao.findByColor(color);
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }

    /**
     * finds a brick with some name
     * @param name name of brick
     * @return brick with desired name
     */
    public List<BrickDto> findByName(String name) {
        if(name == null) {
            throw new DataAccessExceptionService("cannot brick with null name");
        }
        List<Brick> brickEntitites = brickDao.findByName(name);
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }
}

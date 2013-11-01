/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.dao.DataAccessException;

/**
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

    public void create(BrickDto brickDto) {
        if(brickDto == null) {
            throw new DataAccessExceptionService("created brick cannot be null");
        }
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.create(brickEntity);
    }

    public void update(BrickDto brickDto) {
        if(brickDto == null) {
            throw new DataAccessExceptionService("updated brick cannot be null");
        }
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.update(brickEntity);
    }

    public void delete(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("cannot delete brick with null id");
        }
        brickDao.delete(id);
    }

    public List<BrickDto> findAll() {
        List<Brick> brickEntitites = brickDao.findAll();
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }

    public BrickDto findById(Long id) {
        if(id == null) {
            throw new DataAccessExceptionService("cannot find brick with null id");
        }
        Brick entity = brickDao.findById(id);
        return BrickConversion.convertToDto(entity);
    }

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

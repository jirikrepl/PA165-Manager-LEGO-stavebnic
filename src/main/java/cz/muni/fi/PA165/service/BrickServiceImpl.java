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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jiri Krepl
 */
@Service
@Transactional
public class BrickServiceImpl implements BrickService {

    //@Autowired
    private BrickDao brickDao;

    public void setBrickDao(BrickDao brickDao) {
        this.brickDao = brickDao;
    }

    public void create(BrickDto brickDto) {
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.create(brickEntity);
    }

    public void update(BrickDto brickDto) {
        Brick brickEntity = BrickConversion.convertToEntity(brickDto);
        brickDao.update(brickEntity);
    }

    public void delete(Long id) {
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
        Brick entity = brickDao.findById(id);
        return BrickConversion.convertToDto(entity);
    }

    public List<BrickDto> findByColor(Color color) {
        List<Brick> brickEntitites = brickDao.findByColor(color);
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }

    public List<BrickDto> findByName(String name) {
        List<Brick> brickEntitites = brickDao.findByName(name);
        List<BrickDto> brickDtoList = new ArrayList<BrickDto>();
        for (Brick brick : brickEntitites) {
            brickDtoList.add(BrickConversion.convertToDto(brick));
        }
        return brickDtoList;
    }
}

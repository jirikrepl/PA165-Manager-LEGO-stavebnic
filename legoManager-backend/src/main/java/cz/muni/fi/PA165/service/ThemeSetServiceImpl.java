package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.CategoryDto;
import cz.muni.fi.PA165.api.dto.ThemeSetDto;
import cz.muni.fi.PA165.api.service.ThemeSetService;
import cz.muni.fi.PA165.dao.ThemeSetDao;
import cz.muni.fi.PA165.daoDtoConversion.CategoryConversion;
import cz.muni.fi.PA165.daoDtoConversion.ThemeSetConversion;
import cz.muni.fi.PA165.entity.Category;
import cz.muni.fi.PA165.entity.ThemeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.annotation.Secured;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pavol Bako
 */
@Service
@Transactional
public class ThemeSetServiceImpl implements ThemeSetService{
    
    @Autowired
    private ThemeSetDao themeSetDao;

    public void setThemeSetDao(ThemeSetDao themeSetDao) {
        this.themeSetDao = themeSetDao;
    }

    @Secured("ROLE_ADMIN")
    public void create(ThemeSetDto setDto) {

        if (setDto == null) {
            throw new DataAccessExceptionService("DTO can not be NULL");

        }
        ThemeSet ts = ThemeSetConversion.convertToEntity(setDto);
        themeSetDao.create(ts);
    }

    public List<ThemeSetDto> findAll() {
        List<ThemeSet> themeSetEntities = themeSetDao.findAll();
        List<ThemeSetDto> themeSetDtoList = new ArrayList<ThemeSetDto>();
        for (ThemeSet ts : themeSetEntities) {
            themeSetDtoList.add(ThemeSetConversion.convertToDto(ts));
        }
        return themeSetDtoList;
    }

    @Secured("ROLE_ADMIN")
    public void update(ThemeSetDto setDto) {
        if (setDto == null) {
            throw new DataAccessExceptionService("DTO object cannot be NULL");
        }
        ThemeSet ts = ThemeSetConversion.convertToEntity(setDto);
        themeSetDao.update(ts);
    }

    @Secured("ROLE_ADMIN")
    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        themeSetDao.delete(id);
    }

    public ThemeSetDto findById(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("ID cannot be NULL");
        }
        ThemeSet entity = themeSetDao.findById(id);

        ThemeSetDto dto = null;
        try {
            dto = ThemeSetConversion.convertToDto(entity);
        } catch (Exception e) {
            return dto;
        }
        return dto;
    }

    public List<ThemeSetDto> findByCategory(CategoryDto categoryDto){
        if (categoryDto == null){
            throw new DataAccessExceptionService("Category cannost be NULL");
        }
        List<ThemeSet> entities;
        try {
            Category entity = CategoryConversion.convertToEntity(categoryDto);
            entities = themeSetDao.findByCategory(entity);
        }
        catch (DataAccessException e) {
            return new ArrayList<ThemeSetDto>();
        }
        List<ThemeSetDto> dtos = new ArrayList<ThemeSetDto>();
        for(ThemeSet themeSet: entities){
            dtos.add(ThemeSetConversion.convertToDto(themeSet));
        }
        return dtos;
    }
}

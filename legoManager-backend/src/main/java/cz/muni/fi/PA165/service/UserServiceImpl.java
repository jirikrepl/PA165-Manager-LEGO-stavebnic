package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.UserDto;
import cz.muni.fi.PA165.api.service.UserService;
import cz.muni.fi.PA165.daoDtoConversion.UserConversion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.PA165.dao.UserDao;
import cz.muni.fi.PA165.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public void create(UserDto dto) {
        if (dto == null) {
            throw new DataAccessExceptionService("created user cannot be null");
        }
        User userEntity = UserConversion.convertToEntity(dto);
        userDao.create(userEntity);
    }

    @Override
    public void update(UserDto dto) {
        if (dto == null) {
            throw new DataAccessExceptionService("updated user cannot be null");
        }
        User user = UserConversion.convertToEntity(dto);
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("deleted user id cannot be null");
        }
        userDao.delete(id);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userDao.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for(User user: users){
            userDtos.add(UserConversion.convertToDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("cannot find user with null id");
        }
        User user = userDao.findById(id);
        return UserConversion.convertToDto(user);
    }

    @Override
    public List<UserDto> findByName(String name) {
        if (name == null) {
            throw new DataAccessExceptionService("user cannot be with null name");
        }
        List<UserDto> userDtos = new ArrayList<UserDto>();
        List<User> users = userDao.findByName(name);
        for(User user: users){
            userDtos.add(UserConversion.convertToDto(user));
        }
        return userDtos;
    }
}

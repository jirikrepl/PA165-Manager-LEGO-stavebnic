package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.UserDto;

import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */
public interface UserService {

    public void create(UserDto dto);
    public void update(UserDto dto);
    public void delete(Long id);
    public List<UserDto> findAll();
    public UserDto findById(Long id);
    public List<UserDto> findByName(String name);
}

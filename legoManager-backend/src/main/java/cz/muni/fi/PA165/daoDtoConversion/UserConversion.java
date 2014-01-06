package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.api.dto.UserDto;
import cz.muni.fi.PA165.entity.User;
/**
 * Created by PALO on 6.1.14.
 */
public class UserConversion {

    public static UserDto convertToDto (User user){
        if (user == null) {
            throw new IllegalArgumentException("User can not be NULL");
        }
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setIsAdmin(user.getIsAdmin());
        return dto;
    }
    public static User convertToEntity(UserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dto can not be NULL");
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setIsAdmin(dto.getIsAdmin());
        return user;
    }
}

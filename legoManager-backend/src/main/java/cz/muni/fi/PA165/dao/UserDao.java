package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.User;
import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */
public interface UserDao {
    public List<User> findAll();
    public List<User> findByName(String name);
    public User findById(Long id);
    public void create(User user);
    public void update(User user);
    public void delete(Long id);

}

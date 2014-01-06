package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.User;
import org.springframework.dao.DataAccessException;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pavol Bako on 6.1.14.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super();
    }


    public List<User> findAll(){
        Query q = entityManager.createQuery(
                "SELECT u FROM User u", User.class);
        return (List<User>) q.getResultList();
    }

    public List<User> findByName(String name){
        if (name == null) {
            throw new DataAccessException("User name cannot be null."){};
        }
        Query q = entityManager.createQuery("SELECT u FROM User u WHERE name= :user_name", User.class);
        q.setParameter("user_name", name);
        return (List<User>) q.getResultList();
    }



}

package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Account;
import org.springframework.dao.DataAccessException;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Pavol Bako on 6.1.14.
 */
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {

    public AccountDaoImpl() {
        super();
    }


    public List<Account> findAll(){
        Query q = entityManager.createQuery(
                "SELECT u FROM Account u", Account.class);
        return (List<Account>) q.getResultList();
    }

    public Account findByName(String name){
        if (name == null) {
            throw new DataAccessException("Account name cannot be null."){};
        }
        Query q = entityManager.createQuery("SELECT u FROM Account u WHERE name= :account_name", Account.class);
        q.setParameter("account_name", name);
        List<Account> list = (List<Account>) q.getResultList();
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

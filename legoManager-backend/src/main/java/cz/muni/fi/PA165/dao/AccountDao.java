package cz.muni.fi.PA165.dao;

import cz.muni.fi.PA165.entity.Account;
import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */
public interface AccountDao {
    public List<Account> findAll();
    public Account findByName(String name);
    public Account findById(Long id);
    public void create(Account account);
    public void update(Account account);
    public void delete(Long id);

}

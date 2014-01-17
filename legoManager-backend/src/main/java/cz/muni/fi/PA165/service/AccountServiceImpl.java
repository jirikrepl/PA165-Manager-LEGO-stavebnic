package cz.muni.fi.PA165.service;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.api.service.AccountService;
import cz.muni.fi.PA165.dao.AccountDao;
import cz.muni.fi.PA165.daoDtoConversion.AccountConversion;
import cz.muni.fi.PA165.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavol Bako on 6.1.14.
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void create(AccountDto dto) {
        if (dto == null) {
            throw new DataAccessExceptionService("created account cannot be null");
        }
        Account accountEntity = AccountConversion.convertToEntity(dto);
        String passwordHash =
                new ShaPasswordEncoder(256).encodePassword(accountEntity.getPassword(), null);
        accountEntity.setPassword(passwordHash);
        accountDao.create(accountEntity);
    }

    @Override
    public void update(AccountDto dto) {
        if (dto == null) {
            throw new DataAccessExceptionService("updated account cannot be null");
        }
        Account account = AccountConversion.convertToEntity(dto);
        String passwordHash = new ShaPasswordEncoder(256).encodePassword(account.getPassword(), null);
        account.setPassword(passwordHash);
        accountDao.update(account);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("deleted account id cannot be null");
        }
        accountDao.delete(id);
    }

    @Override
    public List<AccountDto> findAll() {
        List<Account> accounts = accountDao.findAll();
        List<AccountDto> accountDtos = new ArrayList<AccountDto>();
        for(Account account : accounts){
            accountDtos.add(AccountConversion.convertToDto(account));
        }
        return accountDtos;
    }

    @Override
    public AccountDto findById(Long id) {
        if (id == null) {
            throw new DataAccessExceptionService("cannot find account with null id");
        }
        Account account = accountDao.findById(id);
        return AccountConversion.convertToDto(account);
    }

    @Override
    public AccountDto findByName(String name) {
        if (name == null) {
            throw new DataAccessExceptionService("account cannot be with null name");
        }
        AccountDto accountDto;
        Account account = accountDao.findByName(name);
        try {
            accountDto = AccountConversion.convertToDto(account);
        } catch (IllegalArgumentException e) {
            return null;
        }

        return accountDto;
    }
}

package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.AccountDto;

import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */
public interface AccountService {

    public void create(AccountDto dto);
    public void update(AccountDto dto);
    public void delete(Long id);
    public List<AccountDto> findAll();
    public AccountDto findById(Long id);
    public AccountDto findByName(String name);
}

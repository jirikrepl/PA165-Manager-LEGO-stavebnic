package cz.muni.fi.PA165.api.service;

import cz.muni.fi.PA165.api.dto.AccountDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * Created by PALO on 6.1.14.
 */
public interface AccountService {

    @Secured("ROLE_ADMIN")
    public void create(AccountDto dto);

    @Secured("ROLE_ADMIN")
    public void update(AccountDto dto);

    @Secured("ROLE_ADMIN")
    public void delete(Long id);

    @Secured("ROLE_ADMIN")
    public List<AccountDto> findAll();

    @Secured("ROLE_ADMIN")
    public AccountDto findById(Long id);

    public AccountDto findByName(String name);
}

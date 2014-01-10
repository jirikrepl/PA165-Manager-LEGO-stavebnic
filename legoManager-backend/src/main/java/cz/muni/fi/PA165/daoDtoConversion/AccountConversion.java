package cz.muni.fi.PA165.daoDtoConversion;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.entity.Account;

/**
 * Created by PALO on 6.1.14.
 */
public class AccountConversion {

    public static AccountDto convertToDto (Account account){
        if (account == null) {
            throw new IllegalArgumentException("Account can not be NULL");
        }
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setPassword(account.getPassword());
        dto.setIsAdmin(account.getIsAdmin());
        return dto;
    }
    public static Account convertToEntity(AccountDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dto can not be NULL");
        }
        Account account = new Account();
        account.setId(dto.getId());
        account.setName(dto.getName());
        account.setPassword(dto.getPassword());
        account.setIsAdmin(dto.getIsAdmin());
        return account;
    }
}

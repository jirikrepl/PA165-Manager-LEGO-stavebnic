package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.api.service.AccountService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import java.util.List;

/**
 * Created by rumanekm on 10.1.14.
 *
 * @author: Martin Rumanek
 */
@UrlBinding("/account/{$event}")
public class AccountActionBean extends BaseActionBean {

    @SpringBean
    private AccountService accountService;
    private List<AccountDto> accounts;
    private AccountDto account;

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    @DefaultHandler
    public Resolution list() {
        accounts = accountService.findAll();
        return new ForwardResolution("/account/accountList.jsp");
    }

    /**
     * creates account
     *
     * @return
     */
    public Resolution createAccount() {
        accountService.create(account);
        return new ForwardResolution(this.getClass(), "list");
    }
}

package cz.muni.fi.PA165.action;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.api.service.AccountService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by rumanekm on 10.1.14.
 *
 * @author: Martin Rumanek
 */
@UrlBinding("/accounts/{$ event}")
public class AccountActionBean extends BaseActionBean {

    @SpringBean
    private AccountService accountService;
    private List<AccountDto> accounts;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createAccount", "updateAccount"}, field = "name", required = true, maxlength = 50),
                    @Validate(on = {"createAccount", "updateAccount"}, field = "password", required = true, minlength = 6, maxlength = 50)
            }
    )
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
        // checkbox is unchecked, in stripes it equals 'false'
        // set false explicitly
        if (account.getIsAdmin() == null) {
            account.setIsAdmin(false);
        }

        // resolve case when userName is already used
        AccountDto usedAccount = accountService.findByName(account.getName());
        if(usedAccount != null) {
            getContext().getMessages().add(new LocalizableError("accounts.usedusername"));
            return new ForwardResolution(this.getClass(), "list");
        }

        accountService.create(account);
        return new ForwardResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        accountService.delete(account.getId());
        return new ForwardResolution(this.getClass(), "list");
    }

    /**
     * redirects to account edit page
     *
     * @return
     */
    public Resolution openEditPage() {
        return new ForwardResolution("/account/accountEdit.jsp");
    }

    public Resolution updateAccount() {
        // checkbox is unchecked, in stripes it equals 'false'
        // set false explicitly
        if (account.getIsAdmin() == null) {
            account.setIsAdmin(false);
        }

        accountService.update(account);
        account = null;
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        accounts = accountService.findAll();
        return null;
    }
}

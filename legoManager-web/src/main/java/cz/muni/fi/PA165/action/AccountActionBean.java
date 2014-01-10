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

    List<AccountDto> accounts;

    @DefaultHandler
    public Resolution list() {
        //TODO remove
//        AccountDto user = new AccountDto();
//        user.setName("user");
//        user.setName("password");
//        user.setIsAdmin(true);
//        accountService.create(user);

        accounts = accountService.findAll();
        return new ForwardResolution("/account/list.jsp");
    }
}

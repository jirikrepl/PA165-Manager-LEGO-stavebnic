package cz.muni.fi.PA165.security;

import cz.muni.fi.PA165.api.dto.AccountDto;
import cz.muni.fi.PA165.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by rumanekm on 12.1.14.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AccountDto accountDto = accountService.findByName(username);


        if (accountDto == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_USER";
                    }
                };
                List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
                listAuthorities.add(grantedAuthority);

                if (accountDto.getIsAdmin()) {
                    GrantedAuthority adminAuthority = new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return "ROLE_ADMIN";
                        }
                    };
                    listAuthorities.add(adminAuthority);
                }

                return Collections.unmodifiableList(listAuthorities);
            }

            @Override
            public String getPassword() {
                return accountDto.getPassword();
            }

            @Override
            public String getUsername() {
                return accountDto.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
}
}

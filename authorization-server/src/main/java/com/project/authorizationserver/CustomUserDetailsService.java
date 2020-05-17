package com.project.authorizationserver;

import com.project.authorizationserver.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
            .map(account->new User(account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("write", "read")))
                .orElseThrow(()->new UsernameNotFoundException("Username does not exist."));
    }

    
}
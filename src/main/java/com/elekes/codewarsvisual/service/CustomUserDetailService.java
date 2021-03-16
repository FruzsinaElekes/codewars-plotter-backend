package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.repository.CodewarsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    CodewarsUserRepository codewarsUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CodewarsUser user = codewarsUserRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
        GrantedAuthority auth = new SimpleGrantedAuthority("User");
        UserDetails details = new User(
                user.getUsername(),
                user.getPassword(),
                List.of(auth)
        );
        return details;
    }
}

package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.model.authentication.LoginCredentials;
import com.elekes.codewarsvisual.model.authentication.Token;
import com.elekes.codewarsvisual.repository.CodewarsUserRepository;
import com.elekes.codewarsvisual.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CodewarsUserRepository codewarsUserRepository;

    public Token attemptLogin(LoginCredentials credentials) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return new Token(jwtUtil.createToken(authentication), jwtUtil.getValidityInMs());
    }

    public String getKeyForUser(String username) {
        String key = codewarsUserRepository.findByUsername(username).map(CodewarsUser::getApi_key).orElse(null);
        return key;
    }

}

package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.model.authentication.LoginCredentials;
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

    public String attemptLogin(LoginCredentials credentials) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return jwtUtil.createToken(authentication);
    }
}

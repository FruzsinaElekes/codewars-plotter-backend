package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.authentication.LoginCredentials;
import com.elekes.codewarsvisual.model.authentication.Token;
import com.elekes.codewarsvisual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Token logIn(@RequestBody LoginCredentials credentials, HttpServletResponse response) {
        Token token = loginService.attemptLogin(credentials);
        Cookie user = new Cookie("username", credentials.getUsername());
        Cookie key = new Cookie("apiKey", loginService.getKeyForUser(credentials.getUsername()));
        key.setHttpOnly(true);
        user.setHttpOnly(true);
        response.addCookie(user);
        response.addCookie(key);
        return token;
    }
}

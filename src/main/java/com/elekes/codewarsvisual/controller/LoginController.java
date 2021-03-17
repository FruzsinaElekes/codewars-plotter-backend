package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.authentication.LoginCredentials;
import com.elekes.codewarsvisual.model.authentication.Token;
import com.elekes.codewarsvisual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Token logIn(@RequestBody LoginCredentials credentials) {
        return loginService.attemptLogin(credentials);
    }
}

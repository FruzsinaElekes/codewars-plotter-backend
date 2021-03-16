package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.authentication.LoginCredentials;
import com.elekes.codewarsvisual.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String logIn(@RequestBody LoginCredentials credentials) {
        return loginService.attemptLogin(credentials);
    }
}

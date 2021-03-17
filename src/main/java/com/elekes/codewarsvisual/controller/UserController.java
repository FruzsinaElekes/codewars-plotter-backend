package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.user.UserSummary;
import com.elekes.codewarsvisual.service.CodeWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private CodeWarsService codeWarsService;

    @GetMapping("/user")
    public UserSummary getUserData(@CookieValue String username, @CookieValue String apiKey) {
        return codeWarsService.getUserFromCodeWars(username, apiKey);
    }
}

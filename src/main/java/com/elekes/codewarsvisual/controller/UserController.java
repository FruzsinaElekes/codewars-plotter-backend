package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.service.CodeWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CodeWarsService codeWarsService;

    @GetMapping("/user/{username}")
    public User getUserData(@PathVariable String username, @RequestParam String apiKey) {
        return codeWarsService.getUserFromCodeWars(username, apiKey);
    }
}

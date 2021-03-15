package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.model.UserSummary;
import com.elekes.codewarsvisual.service.CodeWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    @Autowired
    private CodeWarsService codeWarsService;

    @GetMapping("/user/{username}")
    public UserSummary getUserData(@PathVariable String username, @RequestParam String apiKey) {
        return codeWarsService.getUserFromCodeWars(username, apiKey);
    }
}

package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.RegisteredUser;
import com.elekes.codewarsvisual.model.RegistrationData;
import com.elekes.codewarsvisual.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public RegisteredUser register(@RequestBody RegistrationData regData) {
        RegisteredUser reg = registrationService.registerUser(regData);
        return reg;
    }
}

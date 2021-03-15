package com.elekes.codewarsvisual.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RegistrationData {

    private String username;
    private String apiKey;
    private String password;

}

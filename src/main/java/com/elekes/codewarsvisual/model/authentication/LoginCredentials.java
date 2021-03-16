package com.elekes.codewarsvisual.model.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class LoginCredentials {

    private String username;
    private String password;
}

package com.elekes.codewarsvisual.util;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.model.authentication.RegisteredUser;

public class DbToModelMapper {

    public RegisteredUser mapCodewarsUserToRegisteredUser(CodewarsUser codewarsUser) {
        RegisteredUser registeredUser = RegisteredUser.builder()
                .databaseId(codewarsUser.getId())
                .username(codewarsUser.getUsername())
                .apiKey(codewarsUser.getApi_key())
                .build();
        return registeredUser;
    }
}

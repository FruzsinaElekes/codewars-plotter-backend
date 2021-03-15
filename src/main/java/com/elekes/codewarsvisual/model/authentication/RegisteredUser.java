package com.elekes.codewarsvisual.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RegisteredUser {

    private Long databaseId;
    private String username;
    private String apiKey;
}

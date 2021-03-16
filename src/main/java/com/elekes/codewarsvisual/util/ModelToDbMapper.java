package com.elekes.codewarsvisual.util;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.model.authentication.RegistrationData;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ModelToDbMapper {

    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public CodewarsUser mapRegistrationDataToUser(RegistrationData regData) {
        CodewarsUser codewarsUser = CodewarsUser.builder()
                .username(regData.getUsername())
                .api_key(regData.getApiKey())
                .password(encoder.encode(regData.getPassword()))
                .build();
        return codewarsUser;
    }
}

package com.elekes.codewarsvisual.util;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.model.authentication.RegistrationData;

public class ModelToDbMapper {

    /**
     * password to be encoded when security is added!
     */
    public CodewarsUser mapRegistrationDataToUser(RegistrationData regData) {
        CodewarsUser codewarsUser = CodewarsUser.builder()
                .username(regData.getUsername())
                .api_key(regData.getApiKey())
                .password(regData.getPassword())
                .build();
        return codewarsUser;
    }
}

package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.exception.UserExistsException;
import com.elekes.codewarsvisual.model.RegistrationData;
import com.elekes.codewarsvisual.repository.CodewarsUserRepository;
import com.elekes.codewarsvisual.util.ModelToDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private CodewarsUserRepository codewarsUserRepository;

    private final ModelToDbMapper mapper = new ModelToDbMapper();

    @Transactional
    public CodewarsUser registerUser(RegistrationData regData) {
        Optional<CodewarsUser> codewarsUserOptional = codewarsUserRepository.findByUsername(regData.getUsername());
        if (codewarsUserOptional.isEmpty()) {
            return codewarsUserRepository.save(mapper.mapRegistrationDataToUser(regData));
        } else {
            throw new UserExistsException(regData.getUsername());
        }
    }
}

package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.CodewarsUser;
import com.elekes.codewarsvisual.exception.UserExistsException;
import com.elekes.codewarsvisual.model.authentication.RegisteredUser;
import com.elekes.codewarsvisual.model.authentication.RegistrationData;
import com.elekes.codewarsvisual.repository.CodewarsUserRepository;
import com.elekes.codewarsvisual.util.DbToModelMapper;
import com.elekes.codewarsvisual.util.ModelToDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private CodewarsUserRepository codewarsUserRepository;

    private final ModelToDbMapper modelToDbMapper = new ModelToDbMapper();
    private final DbToModelMapper dbToModelMapper = new DbToModelMapper();

    @Transactional
    public RegisteredUser registerUser(RegistrationData regData) {
        Optional<CodewarsUser> codewarsUserOptional = codewarsUserRepository.findByUsername(regData.getUsername());
        if (codewarsUserOptional.isEmpty()) {
            CodewarsUser savedUser = codewarsUserRepository.save(modelToDbMapper.mapRegistrationDataToUser(regData));
            return dbToModelMapper.mapCodewarsUserToRegisteredUser(savedUser);
        } else {
            throw new UserExistsException(regData.getUsername());
        }
    }
}

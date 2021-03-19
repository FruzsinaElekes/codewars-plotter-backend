package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class KataService {

    @Autowired
    KataRepository kataRepository;

    public List<Kata> getKatasIfCodewarsIdInSet(Set<String> ids) {
        return kataRepository.findByCodewarsIdIn(ids);
    }

    public boolean isChallengeInDatabase(String codewarsId) {
        return kataRepository.findByCodewarsId(codewarsId).isPresent();
    }

    @Transactional
    public void saveKataIfNotInDb(Kata fromApi) {
        if (!isChallengeInDatabase(fromApi.getCodewarsId())) {
            kataRepository.save(fromApi);
        }
    }

}

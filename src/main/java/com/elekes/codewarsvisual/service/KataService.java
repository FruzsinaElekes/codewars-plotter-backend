package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * Class responsible for making Database queries (saving/retrieving Katas, checking for presence in DB)
 */
@Service
public class KataService {

    @Autowired
    KataRepository kataRepository;

    public Map<String, Kata> getKatas(Set<String> ids) {
        return kataRepository.findByCodewarsIdIn(ids)
                .stream()
                .collect(Collectors.toMap(Kata::getCodewarsId, kata -> kata));
    }

    public boolean isKataInDatabase(String codewarsId) {
        return kataRepository.findByCodewarsId(codewarsId).isPresent();
    }

    public void saveKata(Kata fromApi) {
        kataRepository.save(fromApi);
    }

}

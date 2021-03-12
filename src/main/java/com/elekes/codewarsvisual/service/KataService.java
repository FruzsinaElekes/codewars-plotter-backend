package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class KataService {

    @Autowired
    KataRepository kataRepository;

    public List<Kata> getKatasIfCodewarsIdInSet(Set<String> ids) {
        return kataRepository.findByCodewarsIdIn(ids);
    }

    public void saveListOfKatas(List<Kata> katas) {
        kataRepository.saveAll(katas);
    }

}

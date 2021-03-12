package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.entity.Kata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataRetrievalService {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private KataService kataService;


    public List<Kata> getCompletedKatasPerLanguage(String language, String username, String apiKey) {
        List<CompletedChallenge> challenges = codeWarsService.getAllCompletedForUser(username, apiKey);
        Set<String> idSetForLanguage = getIdSetForLanguage(challenges, language);
        List<Kata> katasFromDb = kataService.getKatasIfCodewarsIdInSet(idSetForLanguage);
        Set<String> dbIds = katasFromDb
                .stream()
                .map(Kata::getCodewarsId)
                .collect(Collectors.toSet());
        List<Kata> katasFromApi = idSetForLanguage.stream()
                .filter(id -> !dbIds.contains(id))
                .map(id -> codeWarsService.getCodeChallengeFromCodeWars(id))
                .collect(Collectors.toList());
        kataService.saveListOfKatas(katasFromApi);
        katasFromDb.addAll(katasFromApi);
        return katasFromDb;
    }

    private Set<String> getIdSetForLanguage(List<CompletedChallenge> challenges, String language) {
        return challenges
                .stream()
                .filter(c -> c.getCompletedLanguages().contains(language))
                .map(CompletedChallenge::getId)
                .collect(Collectors.toSet());
    }
}

package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import com.elekes.codewarsvisual.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataRetrievalService {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private KataService kataService;


    public Map<String, Kata> getCompletedKatasPerLanguage(String language, String username) {
        List<CompletedChallenge> challenges = codeWarsService.getAllCompletedForUser(username);

        for (CompletedChallenge completedChallenge : challenges) {
            if (completedChallenge.getCompletedLanguages().contains(language)
                    && !kataService.isChallengeInDatabase(completedChallenge.getId())) {
                Optional<Kata> fromApi = codeWarsService.getCodeChallengeFromCodeWars(completedChallenge.getId());
                fromApi.ifPresent((kata) -> kataService.saveKata(kata));
            }
        }
        return kataService.getKatasIfCodewarsIdInSet(getIdSetForLanguage(challenges, language));
    }


    private Set<String> getIdSetForLanguage(List<CompletedChallenge> challenges, String language) {
        return challenges
                .stream()
                .filter(c -> c.getCompletedLanguages().contains(language))
                .map(CompletedChallenge::getId)
                .collect(Collectors.toSet());
    }


    public List<KataCompletionDetail> getKataCompletionListForUser(String username) {
        List<CompletedChallenge> completed = codeWarsService.getAllCompletedForUser(username);
        Set<String> completedIds = completed.stream().map(CompletedChallenge::getId).collect(Collectors.toSet());
        Map<String, Kata> kataById = kataService.getKatasIfCodewarsIdInSet(completedIds);
        return Mapper.kataMapToCompletionDetailList(kataById, completed);
    }
}

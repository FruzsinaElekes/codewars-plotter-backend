package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import com.elekes.codewarsvisual.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/***
 * Class orchestrating requests to Codewars API and queries to Database
 */
@Service
public class DataRetrievalService {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private KataService kataService;

    /***
     * Fetches all CompletedChallenges from Codewars. Subsequent processing is filtered by language: database is queried
     * for each Kata returned by the API. Kata is saved to DB if not there already.
     * Database is queried for a set of Katas (for the requested language)
     * @param language coding language
     * @param username username to look for
     * @return Map of Katas, with each Kata's codewarsId as key
     */
    public Map<String, Kata> getCompletedKatasPerLanguage(String language, String username) {
        List<CompletedChallenge> challenges = codeWarsService.getAllCompletedForUser(username);

        for (CompletedChallenge completedChallenge : challenges) {
            if (completedChallenge.getCompletedLanguages().contains(language)
                    && !kataService.isKataInDatabase(completedChallenge.getId())) {
                Optional<Kata> fromApi = codeWarsService.getCodeChallengeFromCodeWars(completedChallenge.getId());
                fromApi.ifPresent((kata) -> kataService.saveKata(kata));
            }
        }
        return kataService.getKatas(getIdSetForLanguage(challenges, language));
    }


    private Set<String> getIdSetForLanguage(List<CompletedChallenge> challenges, String language) {
        return challenges
                .stream()
                .filter(c -> c.getCompletedLanguages().contains(language))
                .map(CompletedChallenge::getId)
                .collect(Collectors.toSet());
    }

    /***
     * Fetches all CompletedChallenges from Codewars and supplements them with general information about the Katas
     * stored in our database.
     * @param username username to look for
     * @return List of KataCompletionDetail
     */
    public List<KataCompletionDetail> getKataCompletionListForUser(String username) {
        List<CompletedChallenge> completed = codeWarsService.getAllCompletedForUser(username);
        Set<String> completedIds = completed.stream().map(CompletedChallenge::getId).collect(Collectors.toSet());
        Map<String, Kata> kataById = kataService.getKatas(completedIds);
        return Mapper.kataMapToCompletionDetailList(kataById, completed);
    }
}

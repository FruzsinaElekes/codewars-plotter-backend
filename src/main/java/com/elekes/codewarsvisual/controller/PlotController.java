package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.service.CodeWarsService;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PlotController {

    @Autowired
    CodeWarsService codeWarsService;

    @Autowired
    KataRepository kataRepository;

    /***
     * 1. get completed katas for user
     * 2. find all codewarsId-s in completed list
     * 3. get all db entries in list of codewarsId-s - some will not be found
     * 4.
     * 5. retrieve missing Challenges 1 by 1 - collect in Set
     * 6. persist all new items to db
     *
     * 7. get statistics from db for user for language (count / kyu)
     *
     */
    @GetMapping("/plot/{language}")
    public void getPlotDataForLanguage(@PathVariable String language,
                                       @RequestParam String username,
                                       @RequestParam String apiKey) {
        List<CompletedChallenge> challenges = codeWarsService.getAllCompletedForUser(username, apiKey);
        Set<String> allIds = challenges
                .stream()
                .filter(c -> c.getCompletedLanguages().contains(language))
                .map(CompletedChallenge::getId)
                .collect(Collectors.toSet());
        List<Kata> katasFromDb = kataRepository.findByCodewarsIdIn(allIds);
        Set<String> dbIds = katasFromDb
                .stream()
                .map(Kata::getCodewarsId)
                .collect(Collectors.toSet());
        List<Kata> newKatasFromApi = new LinkedList<>();
        for (String id : allIds) {
            if (!dbIds.contains(id)) {
                newKatasFromApi.add(codeWarsService.getCodeChallengeFromCodeWars(id));
            }
        }
        kataRepository.saveAll(newKatasFromApi);
    }

}

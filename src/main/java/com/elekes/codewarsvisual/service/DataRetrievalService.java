package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataRetrievalService {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private KataService kataService;


    public List<Kata> getCompletedKatasPerLanguage(String language, String username) {
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
        List<Kata> kataList = kataService.getKatasIfCodewarsIdInSet(completedIds);
        List<KataCompletionDetail> kataCompletionDetailList = new ArrayList<>();
        for (Kata kata : kataList) {
            CompletedChallenge completedChallenge = completed
                    .stream()
                    .filter(c -> c.getId().equals(kata.getCodewarsId()))
                    .findFirst().orElse(null);
            KataCompletionDetail kataCompletionDetail = KataCompletionDetail.builder()
                    .completedAt(completedChallenge.getCompletedAt())
                    .completedLanguages(completedChallenge.getCompletedLanguages())
                    .category(kata.getCategory())
                    .codewarsId(kata.getCodewarsId())
                    .createdBy(kata.getCreatedBy())
                    .tags(kata.getTags())
                    .rank(kata.getRank())
                    .url(kata.getUrl())
                    .name(kata.getName())
                    .slug(kata.getSlug())
                    .build();
            kataCompletionDetailList.add(kataCompletionDetail);
        }
        return kataCompletionDetailList;
    }
}

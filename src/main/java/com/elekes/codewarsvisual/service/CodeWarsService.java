package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.codechallenge.CodeChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedPage;
import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import com.elekes.codewarsvisual.model.user.UserSummary;
import com.elekes.codewarsvisual.util.ApiToDbMapper;
import com.elekes.codewarsvisual.util.ApiToModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeWarsService {

    @Value("${codewars.base.url}")
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private ApiToDbMapper mapper = new ApiToDbMapper();
    private ApiToModelMapper apiToModelMapper = new ApiToModelMapper();

    public Kata getCodeChallengeFromCodeWars(String codewarsId) {
        String url = baseUrl + "code-challenges/" + codewarsId;
        CodeChallenge response = restTemplate.getForObject(url, CodeChallenge.class);
        Kata toSave = mapper.codeChallengeToKata.apply(response);
        return toSave;
    }

    public UserSummary getUserFromCodeWars(String username) {
        String url = baseUrl + "users/" + username;
        User user = restTemplate.getForObject(url, User.class);
        UserSummary userSummary = apiToModelMapper.UserToUserSummary(user);
        return userSummary;
    }

    public List<CompletedChallenge> getAllCompletedForUser(String username) {
        String urlNoPage = baseUrl + "users/" + username + "/code-challenges/completed?page=%s";
        int page = 0;
        List<CompletedChallenge> challenges = new ArrayList<>();
        CompletedPage pageResponse = restTemplate.getForObject(String.format(urlNoPage, page), CompletedPage.class);
        challenges.addAll(pageResponse.getData());
        int pagesTotal = pageResponse.getTotalPages();
        if (pagesTotal > 1) {
            for (int i = 1; i < pagesTotal; i++) {
                pageResponse = restTemplate.getForObject(String.format(urlNoPage, i), CompletedPage.class);
                challenges.addAll(pageResponse.getData());
            }
        }
        return challenges;
    }

}

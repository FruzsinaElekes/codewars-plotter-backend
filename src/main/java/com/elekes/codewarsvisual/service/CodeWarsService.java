package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.codechallenge.CodeChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedPage;
import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.util.ApiToDbMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeWarsService {

    @Value("${codewars.base.url}")
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private ApiToDbMapper mapper = new ApiToDbMapper();

    public Kata getCodeChallengeFromCodeWars(String codewarsId) {
        String url = baseUrl + "code-challenges/" + codewarsId;
        CodeChallenge response = restTemplate.getForObject(url, CodeChallenge.class);
        Kata toSave = mapper.codeChallengeToKata.apply(response);
        return toSave;
    }

    /**
     * Needs type for every language maintained by codewars!
     */
    public User getUserFromCodeWars(String username, String apiKey) {
        String url = baseUrl + "users/" + username + "?access_key=" + apiKey;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    public List<CompletedChallenge> getAllCompletedForUser(String username, String apiKey) {
        int page = 0;
        List<CompletedChallenge> challenges = new ArrayList<>();
        CompletedPage pageResponse = restTemplate.getForObject(urlForPage(page, username, apiKey), CompletedPage.class);
        challenges.addAll(pageResponse.getData());
        int pagesTotal = pageResponse.getTotalPages();
        if (pagesTotal > 1) {
            for (int i = 1; i < pagesTotal; i++) {
                pageResponse = restTemplate.getForObject(urlForPage(i, username, apiKey), CompletedPage.class);
                challenges.addAll(pageResponse.getData());
            }
        }
        return challenges;
    }

    private String urlForPage(int p, String username, String apiKey) {
        return baseUrl + "users/" + username + "/code-challenges/completed?page=" + p + ",access_key=" + apiKey;
    }

}

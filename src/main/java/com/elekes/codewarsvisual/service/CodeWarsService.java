package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.apimodel.codechallenge.CodeChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedPage;
import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.UserSummary;
import com.elekes.codewarsvisual.util.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 * Class responsible for making Http requests to the Codewars API
 */
@Service
public class CodeWarsService {

    @Value("${codewars.base.url}")
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    /***
     * Retrieve Information about a Kata from Codewars.
     * We are assuming that Kata information do not change. The returned object is saved into our database and is never
     * fetched from the API again.
     * @param codewarsId id of the requested Kata
     * @return Optional</Kata> to be saved into the database
     */
    public Optional<Kata> getCodeChallengeFromCodeWars(String codewarsId) {
        String url = baseUrl + "code-challenges/" + codewarsId;
        Optional<Kata> toSave;
        try{
            CodeChallenge response = restTemplate.getForObject(url, CodeChallenge.class);
            Kata kata = Mapper.codeChallengeToKata(response);
            toSave = Optional.of(kata);
        } catch (HttpClientErrorException e){
            toSave = Optional.empty();
        }
        return toSave;
    }

    /***
     * Retrieve Information about a User from Codewars.
     * This changes with every newly completed or authored Kata (and other user activities), so this is fetched every time
     * a user's username is entered on the landing page
     * @param username username to look for
     * @return UserSummary object
     */
    public UserSummary getUserFromCodeWars(String username) {
        String url = baseUrl + "users/" + username;
        User user = restTemplate.getForObject(url, User.class);
        UserSummary userSummary = Mapper.UserToUserSummary(user);
        return userSummary;
    }

    /***
     * Retrieve Information about Katas completed by a User from Codewars.
     * May send multiple Http requests to a paginated endpoint, depending on number of solved Katas.
     * @param username username to look for
     * @return List of CompletedChallenges
     */
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

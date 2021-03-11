package com.elekes.codewarsvisual.codewars_service;

import com.elekes.codewarsvisual.apimodel.CodeChallenge;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.repository.KataRepository;
import com.elekes.codewarsvisual.util.ApiToDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CodeWarsService {

    @Value("${codewars.base.url}")
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private KataRepository kataRepository;
    private ApiToDbMapper mapper = new ApiToDbMapper();

    public Kata getCodeChallengeFromCodeWars(String codewarsId) {
        String url = baseUrl + "code-challenges/" + codewarsId;
        CodeChallenge response = restTemplate.getForObject(url, CodeChallenge.class);
        Kata toSave = mapper.codeChallengeToKata.apply(response);
        kataRepository.save(toSave);
        return toSave;
    }

}

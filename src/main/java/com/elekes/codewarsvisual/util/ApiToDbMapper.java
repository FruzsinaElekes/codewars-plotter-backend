package com.elekes.codewarsvisual.util;

import com.elekes.codewarsvisual.apimodel.CodeChallenge;
import com.elekes.codewarsvisual.entity.Kata;

import java.util.function.Function;

public class ApiToDbMapper {

    public Function<CodeChallenge, Kata> codeChallengeToKata = new Function<CodeChallenge, Kata>() {
        @Override
        public Kata apply(CodeChallenge codeChallenge) {
            Kata toSave = new Kata();
            toSave.setCodewarsId(codeChallenge.getId());
            toSave.setCategory(codeChallenge.getCategory());
            toSave.setCreatedBy(codeChallenge.getCreatedBy().getUsername());
            toSave.setDescription(codeChallenge.getDescription());
            toSave.setName(codeChallenge.getName());
            toSave.setSlug(codeChallenge.getSlug());
            toSave.setUrl(codeChallenge.getUrl());
            toSave.setRank(codeChallenge.getRank().getName());
            return toSave;
        }
    };

}

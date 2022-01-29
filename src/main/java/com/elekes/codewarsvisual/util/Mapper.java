package com.elekes.codewarsvisual.util;

import com.elekes.codewarsvisual.apimodel.codechallenge.CodeChallenge;
import com.elekes.codewarsvisual.apimodel.completed.CompletedChallenge;
import com.elekes.codewarsvisual.apimodel.user.Languages;
import com.elekes.codewarsvisual.apimodel.user.ProgrammingLanguage;
import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import com.elekes.codewarsvisual.model.user.LanguageRank;
import com.elekes.codewarsvisual.model.user.UserSummary;

import java.lang.reflect.Field;
import java.util.*;

public class Mapper {

    public static List<KataCompletionDetail> kataMapToCompletionDetailList(Map<String, Kata> kataById, List<CompletedChallenge> completed) {
        List<KataCompletionDetail> kataCompletionDetailList = new ArrayList<>();

        for (CompletedChallenge challenge : completed) {
            Kata kata = kataById.get(challenge.getId());
            kataCompletionDetailList.add(KataCompletionDetail.builder()
                    .completedAt(challenge.getCompletedAt())
                    .completedLanguages(challenge.getCompletedLanguages())
                    .category(kata.getCategory())
                    .codewarsId(kata.getCodewarsId())
                    .createdBy(kata.getCreatedBy())
                    .tags(kata.getTags())
                    .rank(kata.getRank())
                    .url(kata.getUrl())
                    .name(kata.getName())
                    .slug(kata.getSlug())
                    .build());
        }
        return kataCompletionDetailList;
    }

    public static Kata codeChallengeToKata(CodeChallenge challenge){
        Kata toSave = new Kata();
        toSave.setCodewarsId(challenge.getId());
        toSave.setCategory(challenge.getCategory());
        toSave.setCreatedBy(challenge.getCreatedBy() != null? challenge.getCreatedBy().getUsername(): null);
        toSave.setName(challenge.getName());
        toSave.setSlug(challenge.getSlug());
        toSave.setUrl(challenge.getUrl());
        toSave.setRank(challenge.getRank() != null? challenge.getRank().getName(): null);
        toSave.setTags(challenge.getTags());
        return toSave;
    }

    public static UserSummary UserToUserSummary(User user) {
        UserSummary userSummary = UserSummary.builder()
                .username(user.getUsername())
                .name(user.getName())
                .honor(user.getHonor())
                .clan(user.getClan())
                .honor(user.getHonor())
                .leaderboardPosition(user.getLeaderboardPosition())
                .skills(new HashSet<>(user.getSkills() != null? user.getSkills() : new ArrayList<>()))
                .overallRank(LanguageRank.builder()
                        .language("overall")
                        .score(user.getRanks().getOverall().getScore())
                        .color(user.getRanks().getOverall().getColor())
                        .rankName(user.getRanks().getOverall().getName())
                        .build())
                .languageRanks(getSetOfLanguageRankFromLanguages(user.getRanks().getLanguages()))
                .totalAuthored(user.getCodeChallenges().getTotalAuthored())
                .totalCompleted(user.getCodeChallenges().getTotalCompleted())
                .build();
        return userSummary;
    }

    private static Set<LanguageRank> getSetOfLanguageRankFromLanguages(Languages languages) {
        Field[] fields = Languages.class.getDeclaredFields();
        Set<LanguageRank> ranks = new HashSet<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                ProgrammingLanguage pl = (ProgrammingLanguage) field.get(languages);
                if (pl != null) {
                    ranks.add(LanguageRank.builder()
                            .color(pl.getColor())
                            .language(field.getName())
                            .rankName(pl.getName())
                            .score(pl.getScore())
                            .build());
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return ranks;
    }
}

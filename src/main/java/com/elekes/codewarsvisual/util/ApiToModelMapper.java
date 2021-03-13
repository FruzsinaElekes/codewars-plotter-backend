package com.elekes.codewarsvisual.util;


import com.elekes.codewarsvisual.apimodel.user.Languages;
import com.elekes.codewarsvisual.apimodel.user.ProgrammingLanguage;
import com.elekes.codewarsvisual.apimodel.user.User;
import com.elekes.codewarsvisual.model.LanguageRank;
import com.elekes.codewarsvisual.model.UserSummary;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ApiToModelMapper {

    public UserSummary UserToUserSummary(User user) {
        UserSummary userSummary = UserSummary.builder()
                .username(user.getUsername())
                .name(user.getName())
                .honor(user.getHonor())
                .clan(user.getClan())
                .honor(user.getHonor())
                .leaderboardPosition(user.getLeaderboardPosition())
                .skills(new HashSet<String>(user.getSkills()))
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

    private Set<LanguageRank> getSetOfLanguageRankFromLanguages(Languages languages) {
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

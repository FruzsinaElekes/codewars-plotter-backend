package com.elekes.codewarsvisual.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class UserSummary {

    private String username;
    private String name;
    private int honor;
    private String clan;
    private int leaderboardPosition;
    private Set<String> skills;
    private LanguageRank overallRank;
    private Set<LanguageRank> languageRanks;
    private int totalCompleted;
    private int totalAuthored;

}

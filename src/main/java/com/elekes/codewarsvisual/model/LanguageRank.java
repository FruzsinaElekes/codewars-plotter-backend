package com.elekes.codewarsvisual.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LanguageRank {

    private String language;
    private String rankName;
    private int score;
    private String color;

}

package com.elekes.codewarsvisual.apimodel.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgrammingLanguage {

    @JsonProperty("score")
    private int score;

    @JsonProperty("color")
    private String color;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rank")
    private int rank;

    public int getScore(){
        return score;
    }

    public String getColor(){
        return color;
    }

    public String getName(){
        return name;
    }

    public int getRank(){
        return rank;
    }
}

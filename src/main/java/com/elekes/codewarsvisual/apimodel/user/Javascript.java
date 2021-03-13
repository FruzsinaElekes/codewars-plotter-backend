package com.elekes.codewarsvisual.apimodel.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Javascript{

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

	@Override
 	public String toString(){
		return 
			"Javascript{" + 
			"score = '" + score + '\'' + 
			",color = '" + color + '\'' + 
			",name = '" + name + '\'' + 
			",rank = '" + rank + '\'' + 
			"}";
		}
}
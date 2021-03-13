package com.elekes.codewarsvisual.apimodel.codechallenge;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rank{

	@JsonProperty("color")
	private String color;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public String getColor(){
		return color;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}
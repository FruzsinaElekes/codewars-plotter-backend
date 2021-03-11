package com.elekes.codewarsvisual.apimodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatedBy{

	@JsonProperty("url")
	private String url;

	@JsonProperty("username")
	private String username;

	public String getUrl(){
		return url;
	}

	public String getUsername(){
		return username;
	}
}
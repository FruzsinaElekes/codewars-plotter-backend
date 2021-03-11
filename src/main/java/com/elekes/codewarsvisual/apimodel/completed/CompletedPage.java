package com.elekes.codewarsvisual.apimodel.completed;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletedPage {

	@JsonProperty("totalItems")
	private int totalItems;

	@JsonProperty("data")
	private List<CompletedChallenge> data;

	@JsonProperty("totalPages")
	private int totalPages;

	public int getTotalItems(){
		return totalItems;
	}

	public List<CompletedChallenge> getData(){
		return data;
	}

	public int getTotalPages(){
		return totalPages;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"totalItems = '" + totalItems + '\'' + 
			",data = '" + data + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			"}";
		}
}
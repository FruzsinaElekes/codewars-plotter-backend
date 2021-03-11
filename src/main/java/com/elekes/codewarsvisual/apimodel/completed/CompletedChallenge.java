package com.elekes.codewarsvisual.apimodel.completed;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletedChallenge {

	@JsonProperty("completedAt")
	private String completedAt;

	@JsonProperty("name")
	private String name;

	@JsonProperty("completedLanguages")
	private List<String> completedLanguages;

	@JsonProperty("id")
	private String id;

	@JsonProperty("slug")
	private String slug;

	public String getCompletedAt(){
		return completedAt;
	}

	public String getName(){
		return name;
	}

	public List<String> getCompletedLanguages(){
		return completedLanguages;
	}

	public String getId(){
		return id;
	}

	public String getSlug(){
		return slug;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"completedAt = '" + completedAt + '\'' + 
			",name = '" + name + '\'' + 
			",completedLanguages = '" + completedLanguages + '\'' + 
			",id = '" + id + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}
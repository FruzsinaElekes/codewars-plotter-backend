package com.elekes.codewarsvisual.apimodel.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ranks{

	@JsonProperty("languages")
	private Languages languages;

	@JsonProperty("overall")
	private Overall overall;

	public Languages getLanguages(){
		return languages;
	}

	public Overall getOverall(){
		return overall;
	}

	@Override
 	public String toString(){
		return 
			"Ranks{" + 
			"languages = '" + languages + '\'' + 
			",overall = '" + overall + '\'' + 
			"}";
		}
}
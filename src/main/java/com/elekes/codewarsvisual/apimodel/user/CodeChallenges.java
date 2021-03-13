package com.elekes.codewarsvisual.apimodel.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeChallenges{

	@JsonProperty("totalAuthored")
	private int totalAuthored;

	@JsonProperty("totalCompleted")
	private int totalCompleted;

	public int getTotalAuthored(){
		return totalAuthored;
	}

	public int getTotalCompleted(){
		return totalCompleted;
	}

	@Override
 	public String toString(){
		return 
			"CodeChallenges{" + 
			"totalAuthored = '" + totalAuthored + '\'' + 
			",totalCompleted = '" + totalCompleted + '\'' + 
			"}";
		}
}
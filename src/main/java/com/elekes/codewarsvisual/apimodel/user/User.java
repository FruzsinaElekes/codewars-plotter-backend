package com.elekes.codewarsvisual.apimodel.user;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User{

	@JsonProperty("skills")
	private List<String> skills;

	@JsonProperty("ranks")
	private Ranks ranks;

	@JsonProperty("honor")
	private int honor;

	@JsonProperty("codeChallenges")
	private CodeChallenges codeChallenges;

	@JsonProperty("name")
	private String name;

	@JsonProperty("clan")
	private String clan;

	@JsonProperty("leaderboardPosition")
	private int leaderboardPosition;

	@JsonProperty("username")
	private String username;

	public List<String> getSkills(){
		return skills;
	}

	public Ranks getRanks(){
		return ranks;
	}

	public int getHonor(){
		return honor;
	}

	public CodeChallenges getCodeChallenges(){
		return codeChallenges;
	}

	public String getName(){
		return name;
	}

	public String getClan(){
		return clan;
	}

	public int getLeaderboardPosition(){
		return leaderboardPosition;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"skills = '" + skills + '\'' + 
			",ranks = '" + ranks + '\'' + 
			",honor = '" + honor + '\'' + 
			",codeChallenges = '" + codeChallenges + '\'' + 
			",name = '" + name + '\'' + 
			",clan = '" + clan + '\'' + 
			",leaderboardPosition = '" + leaderboardPosition + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
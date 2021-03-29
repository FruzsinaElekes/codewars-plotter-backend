package com.elekes.codewarsvisual.apimodel.codechallenge;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeChallenge{

	@JsonProperty("totalCompleted")
	private int totalCompleted;

    @JsonProperty("publishedAt")
	private String publishedAt;

	@JsonProperty("totalAttempts")
	private int totalAttempts;

	@JsonProperty("approvedBy")
	private ApprovedBy approvedBy;

	@JsonProperty("approvedAt")
	private String approvedAt;

	@JsonProperty("url")
	private String url;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("createdBy")
	private CreatedBy createdBy;

	@JsonProperty("totalStars")
	private int totalStars;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rank")
	private Rank rank;

	@JsonProperty("id")
	private String id;

	@JsonProperty("category")
	private String category;

	@JsonProperty("slug")
	private String slug;

	public int getTotalCompleted(){
		return totalCompleted;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public int getTotalAttempts(){
		return totalAttempts;
	}

	public ApprovedBy getApprovedBy(){
		return approvedBy;
	}

	public String getApprovedAt(){
		return approvedAt;
	}

	public String getUrl(){
		return url;
	}

	public List<String> getTags(){
		return tags;
	}

	public CreatedBy getCreatedBy(){
		return createdBy;
	}

	public int getTotalStars(){
		return totalStars;
	}

	public String getName(){
		return name;
	}

	public Rank getRank(){
		return rank;
	}

	public String getId(){
		return id;
	}

	public String getCategory(){
		return category;
	}

	public String getSlug(){
		return slug;
	}

    @Override
    public String toString() {
        return "CodeChallenge{" +
                "totalCompleted=" + totalCompleted +
                ", publishedAt='" + publishedAt + '\'' +
                ", totalAttempts=" + totalAttempts +
                ", approvedBy=" + approvedBy +
                ", approvedAt='" + approvedAt + '\'' +
                ", url='" + url + '\'' +
                ", tags=" + tags +
                ", createdBy=" + createdBy +
                ", totalStars=" + totalStars +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
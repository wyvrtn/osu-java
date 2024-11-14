package osuapi.enums.comments;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum CommentableType implements DescriptionEnum {
	BEATMAPSET("beatmapset"),
	NEWSPOST("news_post"),
	BUILD("build");
	
	private String description;
	
	private CommentableType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommentableType implements DescriptionEnum<CommentableType> {
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
	
	public CommentableType getEnum(String input) {
		CommentableType result = null;
		for (CommentableType CommentableType : values()) {
			if (StringUtils.equalsIgnoreCase(CommentableType.name(), input) ||
					StringUtils.equalsIgnoreCase(CommentableType.getDescription(), input)) {
				result = CommentableType;
				break;
			}
		}
		return result;
	}
}

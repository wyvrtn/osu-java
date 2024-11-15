package osuapi.enums.comments;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum CommentableOwnerTitle implements DescriptionEnum {
	MAPPER("Mapper");
	
	private String description;
	
	private CommentableOwnerTitle(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

package osuapi.enums.comments;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum CommentSortType implements DescriptionEnum {
	NEW("new"),
	OLD("old"),
	TOP("top");
	
	private String description;
	
	private CommentSortType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

package jospi.enums.comments;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

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

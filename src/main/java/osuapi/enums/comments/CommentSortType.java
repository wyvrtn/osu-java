package osuapi.enums.comments;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum CommentSortType implements DescriptionEnum<CommentSortType> {
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
	
	public CommentSortType getEnum(String input) {
		CommentSortType result = null;
		for (CommentSortType CommentSortType : values()) {
			if (StringUtils.equalsIgnoreCase(CommentSortType.name(), input) ||
					StringUtils.equalsIgnoreCase(CommentSortType.getDescription(), input)) {
				result = CommentSortType;
				break;
			}
		}
		return result;
	}
}

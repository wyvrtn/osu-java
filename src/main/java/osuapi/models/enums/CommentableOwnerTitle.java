package osuapi.models.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommentableOwnerTitle implements DescriptionEnum<CommentableOwnerTitle> {
	MAPPER("Mapper");
	
	private String description;
	
	private CommentableOwnerTitle(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public CommentableOwnerTitle getEnum(String input) {
		CommentableOwnerTitle result = null;
		for (CommentableOwnerTitle CommentableOwnerTitle : values()) {
			if (StringUtils.equalsIgnoreCase(CommentableOwnerTitle.name(), input) ||
					StringUtils.equalsIgnoreCase(CommentableOwnerTitle.getDescription(), input)) {
				result = CommentableOwnerTitle;
				break;
			}
		}
		return result;
	}
}

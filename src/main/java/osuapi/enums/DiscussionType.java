package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DiscussionType implements DescriptionEnum<DiscussionType> {
	HYPE("hype"),
	MAPPERNOTE("note"),
	PRAISE("praise"),
	PROBLEM("problem"),
	REVIEW("review"),
	SUGGESTION("suggestion");
	
	private String description;
	
	private DiscussionType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public DiscussionType getEnum(String input) {
		DiscussionType result = null;
		for (DiscussionType DiscussionType : values()) {
			if (StringUtils.equalsIgnoreCase(DiscussionType.name(), input) ||
					StringUtils.equalsIgnoreCase(DiscussionType.getDescription(), input)) {
				result = DiscussionType;
				break;
			}
		}
		return result;
	}
}

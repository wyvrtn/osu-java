package osuapi.enums.forums;


import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum ForumTopicType implements DescriptionEnum {
	NORMAL("normal"),
	STICKY("sticky"),
	ANNOUNCEMENT("announcement");
	
	private String description;
	
	private ForumTopicType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

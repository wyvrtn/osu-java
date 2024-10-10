package osuapi.enums.forums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum ForumTopicType implements DescriptionEnum<ForumTopicType> {
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
	
	public ForumTopicType getEnum(String input) {
		ForumTopicType result = null;
		for (ForumTopicType ForumTopicType : values()) {
			if (StringUtils.equalsIgnoreCase(ForumTopicType.name(), input) ||
					StringUtils.equalsIgnoreCase(ForumTopicType.getDescription(), input)) {
				result = ForumTopicType;
				break;
			}
		}
		return result;
	}
}

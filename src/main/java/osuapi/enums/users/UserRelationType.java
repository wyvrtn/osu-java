package osuapi.enums.users;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum UserRelationType implements DescriptionEnum<UserRelationType> {
	BLOCK("block"),
	FRIEND("friend");
	
	private String description;
	
	private UserRelationType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public UserRelationType getEnum(String input) {
		UserRelationType result = null;
		for (UserRelationType UserRelationType : values()) {
			if (StringUtils.equalsIgnoreCase(UserRelationType.name(), input) ||
					StringUtils.equalsIgnoreCase(UserRelationType.getDescription(), input)) {
				result = UserRelationType;
				break;
			}
		}
		return result;
	}
}

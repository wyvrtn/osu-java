package osuapi.enums.users;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserScoreType {
	RECENT("recent"),
    BEST("best"),
	FIRST("firsts");
	
	private String description;
	
	private UserScoreType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public UserScoreType getEnum(String input) {
		UserScoreType result = null;
		for (UserScoreType UserScoreType : values()) {
			if (StringUtils.equalsIgnoreCase(UserScoreType.name(), input) ||
					StringUtils.equalsIgnoreCase(UserScoreType.getDescription(), input)) {
				result = UserScoreType;
				break;
			}
		}
		return result;
	}
}

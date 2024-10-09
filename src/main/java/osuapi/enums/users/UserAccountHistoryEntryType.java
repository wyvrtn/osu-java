package osuapi.enums.users;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum UserAccountHistoryEntryType implements DescriptionEnum<UserAccountHistoryEntryType> {
	NOTE("note"),
	RESTRICTION("restriction"),
	SILENCE("silence"),
	TOURNAMENTBAN("tournament_ban");
	
	private String description;
	
	private UserAccountHistoryEntryType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public UserAccountHistoryEntryType getEnum(String input) {
		UserAccountHistoryEntryType result = null;
		for (UserAccountHistoryEntryType UserAccountHistoryEntryType : values()) {
			if (StringUtils.equalsIgnoreCase(UserAccountHistoryEntryType.name(), input) ||
					StringUtils.equalsIgnoreCase(UserAccountHistoryEntryType.getDescription(), input)) {
				result = UserAccountHistoryEntryType;
				break;
			}
		}
		return result;
	}
}

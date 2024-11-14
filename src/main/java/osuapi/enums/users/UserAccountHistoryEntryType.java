package osuapi.enums.users;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum UserAccountHistoryEntryType implements DescriptionEnum {
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
}

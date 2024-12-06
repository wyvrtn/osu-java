package jospi.enums.users;

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
}

package jospi.enums.matches;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MatchEventType implements DescriptionEnum {
    HOST_CHANGED("host-changed"),
	MATCH_CREATED("match-created"),
    MATCH_DISBANDED("match-disbanded"),
    OTHER("other"),
    PLAYER_JOINED("player-joined"),
    PLAYER_KICKED("player-kicked"),
    PLAYER_LEFT("player-left");

	private String description;

	private MatchEventType(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

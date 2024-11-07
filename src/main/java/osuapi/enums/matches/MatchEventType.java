package osuapi.enums.matches;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MatchEventType implements DescriptionEnum<MatchEventType> {
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
	
	public MatchEventType getEnum(String input) {
		MatchEventType result = null;
		for (MatchEventType MatchEventType : values()) {
			if (StringUtils.equalsIgnoreCase(MatchEventType.name(), input) ||
					StringUtils.equalsIgnoreCase(MatchEventType.getDescription(), input)) {
				result = MatchEventType;
				break;
			}
		}
		return result;
	}
}

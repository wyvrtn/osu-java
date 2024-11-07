package osuapi.enums.multiplayer;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MultiplayerRoomMode implements DescriptionEnum<MultiplayerRoomMode> {
    ACTIVE("active"),
	ALL("all"),
	ENDED("ended"),
	PARTICIPATED("participated"),
    OWNED("owned");
	
	private String description;
	
	private MultiplayerRoomMode(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public MultiplayerRoomMode getEnum(String input) {
		MultiplayerRoomMode result = null;
		for (MultiplayerRoomMode MultiplayerRoomMode : values()) {
			if (StringUtils.equalsIgnoreCase(MultiplayerRoomMode.name(), input) ||
					StringUtils.equalsIgnoreCase(MultiplayerRoomMode.getDescription(), input)) {
				result = MultiplayerRoomMode;
				break;
			}
		}
		return result;
	}
}

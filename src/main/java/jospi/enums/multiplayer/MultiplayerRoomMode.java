package jospi.enums.multiplayer;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MultiplayerRoomMode implements DescriptionEnum {
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
}

package jospi.enums.multiplayer;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MultiplayerRoomTypeGroup implements DescriptionEnum {
	PLAYLISTS("playlists"),
	REALTIME("realtime");

	private String description;

	private MultiplayerRoomTypeGroup(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

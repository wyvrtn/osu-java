package osuapi.enums.multiplayers;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MultiplayerRoomTypeGroup implements DescriptionEnum<MultiplayerRoomTypeGroup> {
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
	
	public MultiplayerRoomTypeGroup getEnum(String input) {
		MultiplayerRoomTypeGroup result = null;
		for (MultiplayerRoomTypeGroup MultiplayerRoomTypeGroup : values()) {
			if (StringUtils.equalsIgnoreCase(MultiplayerRoomTypeGroup.name(), input) ||
					StringUtils.equalsIgnoreCase(MultiplayerRoomTypeGroup.getDescription(), input)) {
				result = MultiplayerRoomTypeGroup;
				break;
			}
		}
		return result;
	}
}

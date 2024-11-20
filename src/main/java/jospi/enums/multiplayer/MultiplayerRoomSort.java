package jospi.enums.multiplayer;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MultiplayerRoomSort implements DescriptionEnum {
	ENDED("ended"),
	CREATED("created");
	
	private String description;
	
	private MultiplayerRoomSort(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

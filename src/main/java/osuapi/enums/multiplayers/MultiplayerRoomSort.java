package osuapi.enums.multiplayers;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MultiplayerRoomSort implements DescriptionEnum<MultiplayerRoomSort> {
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
	
	public MultiplayerRoomSort getEnum(String input) {
		MultiplayerRoomSort result = null;
		for (MultiplayerRoomSort MultiplayerRoomSort : values()) {
			if (StringUtils.equalsIgnoreCase(MultiplayerRoomSort.name(), input) ||
					StringUtils.equalsIgnoreCase(MultiplayerRoomSort.getDescription(), input)) {
				result = MultiplayerRoomSort;
				break;
			}
		}
		return result;
	}
}

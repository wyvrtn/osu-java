package jospi.enums.beatmaps;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum RankedStatus implements DescriptionEnum {
	GRAVEYARD(-2, "graveyard"),
	WIP(-1, "wip"),
	PENDING(0, "pending"),
	RANKED(1, "ranked"),
	APPROVED(2, "approved"),
	QUALIFIED(3, "qualified"),
	LOVED(4, "loved");
	
	private int value;
	private String description;
	
	private RankedStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public int getValue() {
		return this.value;
	}
}

package osuapi.models.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RankedStatus implements DescriptionEnum<RankedStatus> {
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
	
	public RankedStatus getEnum(String input) {
		RankedStatus result = null;
		for (RankedStatus RankedStatus : values()) {
			if (StringUtils.equalsIgnoreCase(RankedStatus.name(), input) ||
					RankedStatus.getValue()==Integer.parseInt(input)) {
				result = RankedStatus;
				break;
			}
			if (StringUtils.equalsIgnoreCase("pending", input)) {
				result = RankedStatus;
			}
		}
		return result;
	}
}

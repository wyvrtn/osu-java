package osuapi.enums.beatmaps;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum BeatmapsetEventApproval implements DescriptionEnum {
	RANKED("ranked"),
	APPROVED("approved"),
	QUALIFIED("qualified"),
	LOVED("loved");
	
	private String description;
	
	private BeatmapsetEventApproval(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}
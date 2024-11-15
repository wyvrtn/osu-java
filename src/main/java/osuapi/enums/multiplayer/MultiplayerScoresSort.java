package osuapi.enums.multiplayer;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MultiplayerScoresSort implements DescriptionEnum {
	SCORE_ASC("score_asc"),
	SCORE_DESC("score_desc");
	
	private String description;
	
	private MultiplayerScoresSort(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

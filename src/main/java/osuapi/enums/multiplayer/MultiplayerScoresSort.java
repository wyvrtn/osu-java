package osuapi.enums.multiplayer;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MultiplayerScoresSort implements DescriptionEnum<MultiplayerScoresSort> {
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
	
	public MultiplayerScoresSort getEnum(String input) {
		MultiplayerScoresSort result = null;
		for (MultiplayerScoresSort MultiplayerScoresSort : values()) {
			if (StringUtils.equalsIgnoreCase(MultiplayerScoresSort.name(), input) ||
					StringUtils.equalsIgnoreCase(MultiplayerScoresSort.getDescription(), input)) {
				result = MultiplayerScoresSort;
				break;
			}
		}
		return result;
	}
}

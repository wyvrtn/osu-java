package jospi.enums.matches;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MatchScoringType implements DescriptionEnum {
    ACCURACY("accuracy"),
	COMBO("combo"),
    SCORE("score"),
    SCOREV2("scorev2");
	
	private String description;
	
	private MatchScoringType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

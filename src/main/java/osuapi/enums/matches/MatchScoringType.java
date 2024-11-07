package osuapi.enums.matches;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MatchScoringType implements DescriptionEnum<MatchScoringType> {
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
	
	public MatchScoringType getEnum(String input) {
		MatchScoringType result = null;
		for (MatchScoringType MatchScoringType : values()) {
			if (StringUtils.equalsIgnoreCase(MatchScoringType.name(), input) ||
					StringUtils.equalsIgnoreCase(MatchScoringType.getDescription(), input)) {
				result = MatchScoringType;
				break;
			}
		}
		return result;
	}
}

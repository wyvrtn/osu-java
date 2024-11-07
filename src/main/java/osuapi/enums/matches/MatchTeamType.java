package osuapi.enums.matches;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MatchTeamType implements DescriptionEnum<MatchTeamType> {
    HEAD_TO_HEAD("head-to-head"),
	TAG_COOP("tag-coop"),
    TAG_TEAM_VS("tag-team-vs"),
    TEAM_VS("team-vs");
	
	private String description;
	
	private MatchTeamType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public MatchTeamType getEnum(String input) {
		MatchTeamType result = null;
		for (MatchTeamType MatchTeamType : values()) {
			if (StringUtils.equalsIgnoreCase(MatchTeamType.name(), input) ||
					StringUtils.equalsIgnoreCase(MatchTeamType.getDescription(), input)) {
				result = MatchTeamType;
				break;
			}
		}
		return result;
	}
}

package osuapi.enums.rankings;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum UserRankingType implements DescriptionEnum<UserRankingType> {
	SPOTLIGHT("charts"),
	COUNTRY("country"),
	PERFORMANCE("performance"),
	SCORE("score");
	
	private String description;
	
	private UserRankingType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public UserRankingType getEnum(String input) {
		UserRankingType result = null;
		for (UserRankingType RankingType : values()) {
			if (StringUtils.equalsIgnoreCase(RankingType.name(), input) ||
					StringUtils.equalsIgnoreCase(RankingType.getDescription(), input)) {
				result = RankingType;
				break;
			}
		}
		return result;
	}
}

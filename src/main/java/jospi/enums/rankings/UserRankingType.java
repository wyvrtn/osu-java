package jospi.enums.rankings;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum UserRankingType implements DescriptionEnum {
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
}

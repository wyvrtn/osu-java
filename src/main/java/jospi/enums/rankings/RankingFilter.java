package jospi.enums.rankings;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum RankingFilter implements DescriptionEnum {
	ALL("all"),
	FRIENDS("friends");

	private String description;

	private RankingFilter(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

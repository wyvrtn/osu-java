package osuapi.enums.rankings;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

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

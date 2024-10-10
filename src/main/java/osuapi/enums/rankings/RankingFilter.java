package osuapi.enums.rankings;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum RankingFilter implements DescriptionEnum<RankingFilter> {
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
	
	public RankingFilter getEnum(String input) {
		RankingFilter result = null;
		for (RankingFilter RankingFilter : values()) {
			if (StringUtils.equalsIgnoreCase(RankingFilter.name(), input) ||
					StringUtils.equalsIgnoreCase(RankingFilter.getDescription(), input)) {
				result = RankingFilter;
				break;
			}
		}
		return result;
	}
}

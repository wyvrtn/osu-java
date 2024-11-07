package osuapi.enums.home;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum HomeSearchQueryMode implements DescriptionEnum<HomeSearchQueryMode> {
    USER("user"),
	WIKIPAGE("wiki_page"),
    ALL("all");
	
	private String description;
	
	private HomeSearchQueryMode(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public HomeSearchQueryMode getEnum(String input) {
		HomeSearchQueryMode result = null;
		for (HomeSearchQueryMode HomeSearchQueryMode : values()) {
			if (StringUtils.equalsIgnoreCase(HomeSearchQueryMode.name(), input) ||
					StringUtils.equalsIgnoreCase(HomeSearchQueryMode.getDescription(), input)) {
				result = HomeSearchQueryMode;
				break;
			}
		}
		return result;
	}
}

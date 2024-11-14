package osuapi.enums.home;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum HomeSearchQueryMode implements DescriptionEnum {
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
}

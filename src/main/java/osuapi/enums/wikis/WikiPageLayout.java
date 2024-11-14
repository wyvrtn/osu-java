package osuapi.enums.wikis;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum WikiPageLayout implements DescriptionEnum {
	MARKDOWN("markdown_page"),
	MAIN("main_page");
	
	private String description;
	
	private WikiPageLayout(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WikiPageLayout implements DescriptionEnum<WikiPageLayout> {
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
	
	public WikiPageLayout getEnum(String input) {
		WikiPageLayout result = null;
		for (WikiPageLayout WikiPageLayoutType : values()) {
			if (StringUtils.equalsIgnoreCase(WikiPageLayoutType.name(), input) ||
					StringUtils.equalsIgnoreCase(WikiPageLayoutType.getDescription(), input)) {
				result = WikiPageLayoutType;
				break;
			}
		}
		return result;
	}
}

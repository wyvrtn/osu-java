package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WikiPageLayoutType implements DescriptionEnum<WikiPageLayoutType> {
	MARKDOWN("markdown_page"),
	MAIN("main_page");
	
	private String description;
	
	private WikiPageLayoutType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public WikiPageLayoutType getEnum(String input) {
		WikiPageLayoutType result = null;
		for (WikiPageLayoutType WikiPageLayoutType : values()) {
			if (StringUtils.equalsIgnoreCase(WikiPageLayoutType.name(), input) ||
					StringUtils.equalsIgnoreCase(WikiPageLayoutType.getDescription(), input)) {
				result = WikiPageLayoutType;
				break;
			}
		}
		return result;
	}
}

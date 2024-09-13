package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChangelogEntryType implements DescriptionEnum<ChangelogEntryType> {
	ADD("add"),
	FIX("fix"),
	MISCELLANEOUS("misc");
	
	private String description;
	
	private ChangelogEntryType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public ChangelogEntryType getEnum(String input) {
		ChangelogEntryType result = null;
		for (ChangelogEntryType ChangelogEntryType : values()) {
			if (StringUtils.equalsIgnoreCase(ChangelogEntryType.name(), input) ||
					StringUtils.equalsIgnoreCase(ChangelogEntryType.getDescription(), input)) {
				result = ChangelogEntryType;
				break;
			}
		}
		return result;
	}
}

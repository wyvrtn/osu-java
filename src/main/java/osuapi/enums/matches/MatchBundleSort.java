package osuapi.enums.matches;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum MatchBundleSort implements DescriptionEnum<MatchBundleSort> {
	ID_ASC("id_asc"),
	ID_DESC("id_desc");
	
	private String description;
	
	private MatchBundleSort(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public MatchBundleSort getEnum(String input) {
		MatchBundleSort result = null;
		for (MatchBundleSort MatchBundleSort : values()) {
			if (StringUtils.equalsIgnoreCase(MatchBundleSort.name(), input) ||
					StringUtils.equalsIgnoreCase(MatchBundleSort.getDescription(), input)) {
				result = MatchBundleSort;
				break;
			}
		}
		return result;
	}
}

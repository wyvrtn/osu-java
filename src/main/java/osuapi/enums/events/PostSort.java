package osuapi.enums.events;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum PostSort implements DescriptionEnum<PostSort> {
	IDASCENDING("IDAscending"),
	IDDESCENDING("IDDescending");
	
	private String description;
	
	private PostSort(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public PostSort getEnum(String input) {
		PostSort result = null;
		for (PostSort PostSort : values()) {
			if (StringUtils.equalsIgnoreCase(PostSort.name(), input) ||
					StringUtils.equalsIgnoreCase(PostSort.getDescription(), input)) {
				result = PostSort;
				break;
			}
		}
		return result;
	}
}

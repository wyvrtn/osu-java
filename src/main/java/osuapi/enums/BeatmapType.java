package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BeatmapType implements DescriptionEnum<BeatmapType> {
	FAVOURITE("favourite"),
	GRAVEYARD("graveyard"),
	GUEST("guest"),
	LOVED("loved"),
	MOST_PLAYED("most_played"),
	NOMINATED("nominated"),
	PENDING("pending"),
    RANKED("ranked");
	
	private String description;
	
	private BeatmapType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public BeatmapType getEnum(String input) {
		BeatmapType result = null;
		for (BeatmapType BeatmapType : values()) {
			if (StringUtils.equalsIgnoreCase(BeatmapType.name(), input) ||
					StringUtils.equalsIgnoreCase(BeatmapType.getDescription(), input)) {
				result = BeatmapType;
				break;
			}
		}
		return result;
	}
}
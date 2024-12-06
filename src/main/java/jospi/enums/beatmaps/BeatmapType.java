package jospi.enums.beatmaps;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum BeatmapType implements DescriptionEnum {
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
}

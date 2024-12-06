package jospi.enums.beatmaps;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum BeatmapPackType implements DescriptionEnum {
    STANDARD("standard"),
    FEATURED("featured"),
    TOURNAMENT("tournament"),
    LOVED("loved"),
    CHART("chart"),
    THEME("theme"),
    ARTIST("artist");

    private String description;

    private BeatmapPackType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

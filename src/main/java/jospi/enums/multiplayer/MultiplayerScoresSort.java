package jospi.enums.multiplayer;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MultiplayerScoresSort implements DescriptionEnum {
    SCORE_ASC("score_asc"),
    SCORE_DESC("score_desc");

    private String description;

    private MultiplayerScoresSort(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

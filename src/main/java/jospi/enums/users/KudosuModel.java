package jospi.enums.users;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum KudosuModel implements DescriptionEnum {
    FORUM_POST("forum_post"),
    BEATMAP_DISCUSSION("beatmap_discussion");

    private String description;

    private KudosuModel(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

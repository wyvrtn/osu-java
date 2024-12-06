package jospi.enums.misc;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum Ruleset implements DescriptionEnum {
    OSU("osu"),
    TAIKO("taiko"),
    CATCH("fruits"),
    MANIA("mania");

    private String description;

    private Ruleset(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

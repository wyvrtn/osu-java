package jospi.enums.changelogs;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum ChangelogEntryType implements DescriptionEnum {
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
}

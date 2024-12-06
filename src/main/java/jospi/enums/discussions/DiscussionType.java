package jospi.enums.discussions;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum DiscussionType implements DescriptionEnum {
    HYPE("hype"),
    MAPPERNOTE("note"),
    PRAISE("praise"),
    PROBLEM("problem"),
    REVIEW("review"),
    SUGGESTION("suggestion");

    private String description;

    private DiscussionType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

package jospi.enums.users;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum UserRelationType implements DescriptionEnum {
    BLOCK("block"),
    FRIEND("friend");

    private String description;

    private UserRelationType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

package jospi.enums.events;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum PostSort implements DescriptionEnum {
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
}

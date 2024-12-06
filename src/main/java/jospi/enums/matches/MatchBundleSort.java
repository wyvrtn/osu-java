package jospi.enums.matches;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MatchBundleSort implements DescriptionEnum {
    ID_ASC("id_asc"),
    ID_DESC("id_desc");

    private String description;

    private MatchBundleSort(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

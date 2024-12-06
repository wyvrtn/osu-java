package jospi.enums.matches;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum MatchTeamType implements DescriptionEnum {
    HEAD_TO_HEAD("head-to-head"),
    TAG_COOP("tag-coop"),
    TAG_TEAM_VS("tag-team-vs"),
    TEAM_VS("team-vs");

    private String description;

    private MatchTeamType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return this.description;
    }
}

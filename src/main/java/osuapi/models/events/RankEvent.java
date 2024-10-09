package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.Grade;
import osuapi.enums.Ruleset;

@Getter
@Setter
@NoArgsConstructor
public class RankEvent extends Event {

    @JsonProperty("rank")
    private int rank;

    @JsonProperty("scoreRank")
    private Grade scoreRank;

    @JsonProperty("mode")
    private Ruleset mode;

    @JsonProperty("beatmap")
    private EventBeatmap beatmap;

    @JsonProperty("user")
    private EventUser user;
}

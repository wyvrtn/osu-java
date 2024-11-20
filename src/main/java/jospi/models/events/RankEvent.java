package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.misc.Grade;
import jospi.enums.misc.Ruleset;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

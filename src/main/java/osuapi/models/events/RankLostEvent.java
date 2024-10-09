package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.Ruleset;

@Getter
@Setter
@NoArgsConstructor
public class RankLostEvent extends Event {
    @JsonProperty("mode")
    private Ruleset mode;

    @JsonProperty("beatmap")
    private EventBeatmap beatmap;

    @JsonProperty("user")
    private EventUser user;
}

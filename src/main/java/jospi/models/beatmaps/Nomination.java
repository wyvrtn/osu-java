package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.misc.Ruleset;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Nomination {
    @JsonProperty("beatmapset_id")
    private int beatmapSetId;

    @JsonProperty("rulesets")
    private Ruleset[] rulesets;

    @JsonProperty("reset")
    private boolean reset;

    @JsonProperty("user_id")
    private int UserId;
}

package jospi.models.matches;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.matches.MatchScoringType;
import jospi.enums.matches.MatchTeamType;
import jospi.enums.misc.Ruleset;
import jospi.models.beatmaps.Beatmap;
import jospi.models.scores.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchGame {

    @JsonProperty("id")
    private int id;

    @JsonProperty("beatmap")
    private Beatmap beatmap;

    @JsonProperty("beatmap_id")
    private int beatmapId;

    @JsonProperty("start_time")
    private OffsetDateTime startTime;

    @JsonProperty("end_time")
    private OffsetDateTime endTime;

    @JsonProperty("mode")
    private Ruleset mode;

    @JsonProperty("mode_int")
    private int modeInt;

    @JsonProperty("mods")
    private String[] mods;

    @JsonProperty("scores")
    private Score[] scores;

    @JsonProperty("scoring_type")
    private MatchScoringType scoringType;

    @JsonProperty("team_type")
    private MatchTeamType teamType;
}

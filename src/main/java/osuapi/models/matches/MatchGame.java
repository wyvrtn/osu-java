package osuapi.models.matches;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.matches.MatchScoringType;
import osuapi.enums.matches.MatchTeamType;
import osuapi.enums.misc.Ruleset;
import osuapi.models.beatmaps.Beatmap;
import osuapi.models.scores.Score;

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

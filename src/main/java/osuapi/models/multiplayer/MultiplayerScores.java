package osuapi.models.multiplayer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.scores.Score;

@Getter
@Setter
@NoArgsConstructor
public class MultiplayerScores {
    @JsonProperty("cursor_string")
    private String cursorString;

    @JsonProperty("params")
    private Object params;

    @JsonProperty("scores")
    private Score[] scores;

    @JsonProperty("total")
    private int total;

    @JsonProperty("user_score")
    private Score userScore;
}

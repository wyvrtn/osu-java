package jospi.models.matches;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchEvent {

    @JsonProperty("id")
    private int id;

    @JsonProperty("detail")
    private MatchDetail detail;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("game")
    private MatchGame game;
}

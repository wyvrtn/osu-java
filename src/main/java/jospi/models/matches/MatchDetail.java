package jospi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.matches.MatchEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchDetail {

    @JsonProperty("type")
    private MatchEventType type;

    @JsonProperty("text")
    private String text;
}

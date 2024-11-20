package jospi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchesBundle {

    @JsonProperty("cursor_string")
    private String cursorString;

    @JsonProperty("matches")
    private Match[] matches;

    @JsonProperty("params")
    private MatchParams params;
}

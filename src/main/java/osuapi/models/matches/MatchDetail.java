package osuapi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.matches.MatchEventType;

@Getter
@Setter
@NoArgsConstructor
public class MatchDetail {

    @JsonProperty("type")
    private MatchEventType type;

    @JsonProperty("text")
    private String text;
}

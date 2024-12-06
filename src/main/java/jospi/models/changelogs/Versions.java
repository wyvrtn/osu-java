package jospi.models.changelogs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Versions {

    @JsonProperty("next")
    private Build next;

    @JsonProperty("previous")
    private Build previous;
}

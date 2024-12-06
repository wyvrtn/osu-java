package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapSetDescription {

    @JsonProperty("bbcode")
    private String bbCode;

    @JsonProperty("description")
    private String description;
}

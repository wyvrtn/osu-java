package jospi.models.rankings;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.beatmaps.BeatmapSetExtended;
import jospi.models.users.UserStatistics;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankingsBundle {

    @JsonProperty("beatmapsets")
    private BeatmapSetExtended[] beatmapsets;

    @JsonProperty("cursor")
    private String cursorString;

    @JsonProperty("ranking")
    private UserStatistics[] rankings;

    @JsonProperty("spotlight")
    private Spotlight spotlight;

    @JsonProperty("total")
    private int total;
}

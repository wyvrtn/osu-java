package osuapi.models.rankings;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.beatmaps.BeatmapSetExtended;
import osuapi.models.users.UserStatistics;

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

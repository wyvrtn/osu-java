package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.beatmaps.Beatmap;
import jospi.models.beatmaps.BeatmapSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapPlaycount {

    @JsonProperty("beatmap_id")
    private int beatmapId;

    @JsonProperty("beatmap")
    private Beatmap beatmap;

    @JsonProperty("beatmapset")
    private BeatmapSet beatmapSet;

    @JsonProperty("count")
    private int count;
}

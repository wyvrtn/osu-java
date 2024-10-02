package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.beatmaps.Beatmap;
import osuapi.models.beatmaps.BeatmapSet;

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

package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapPlaycountEvent extends Event {
    
    @JsonProperty("beatmap")
    private EventBeatmap beatmap;

    @JsonProperty("count")
    private int count;
}

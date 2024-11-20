package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapsetDeleteEvent extends Event {
    @JsonProperty("beatmapset")
    private EventBeatmapset beatmapset;
}

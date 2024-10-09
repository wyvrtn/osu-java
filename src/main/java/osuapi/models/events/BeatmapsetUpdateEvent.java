package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapsetUpdateEvent extends Event {
    @JsonProperty("beatmapset")
    private EventBeatmapset beatmapset;

    @JsonProperty("user")
    private EventUser user;
}

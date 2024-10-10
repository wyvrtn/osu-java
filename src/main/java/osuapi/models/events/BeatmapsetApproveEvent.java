package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.beatmaps.BeatmapsetEventApproval;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapsetApproveEvent extends Event {

    @JsonProperty("approval")
    private BeatmapsetEventApproval approval;

    @JsonProperty("beatmapset")
    private EventBeatmapset beatmapset;

    @JsonProperty("user")
    private EventUser user;
}

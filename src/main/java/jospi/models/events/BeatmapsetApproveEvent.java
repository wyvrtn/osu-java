package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.beatmaps.BeatmapsetEventApproval;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

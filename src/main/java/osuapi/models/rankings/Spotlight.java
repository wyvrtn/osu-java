package osuapi.models.rankings;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Spotlight {

    @JsonProperty("end_date")
    private OffsetDateTime endDate;

    @JsonProperty("id")
    private int id;

    @JsonProperty("mode_specific")
    private boolean modeSpecific;

    @JsonProperty("participant_count")
    private int participantCount;

    @JsonProperty("start_date")
    private OffsetDateTime startDate;

    @JsonProperty("type")
    private String spotlightType;
}

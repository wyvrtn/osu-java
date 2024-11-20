package jospi.models.matches;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match {

    @JsonProperty("id")
    private int id;

    @JsonProperty("start_time")
    private OffsetDateTime startTime;

    @JsonProperty("end_time")
    private OffsetDateTime endTime;

    @JsonProperty("name")
    private String name;
}

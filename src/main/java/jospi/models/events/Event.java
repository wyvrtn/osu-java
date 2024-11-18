package jospi.models.events;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.events.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private EventType type;
}

package jospi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchBundle {

    @JsonProperty("match")
    private Match match;

    @JsonProperty("events")
    private MatchEvent[] events;

    @JsonProperty("users")
    private User[] users;

    @JsonProperty("first_event_id")
    private int firstEventId;

    @JsonProperty("latest_event_id")
    private int latestEventId;
}

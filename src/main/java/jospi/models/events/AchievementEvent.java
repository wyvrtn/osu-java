package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.users.Achievement;

public class AchievementEvent extends Event {

    @JsonProperty("achievement")
    private Achievement achievement;

    @JsonProperty("user")
    private EventUser user;
}

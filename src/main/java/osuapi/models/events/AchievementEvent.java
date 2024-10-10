package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import osuapi.models.users.Achievement;

public class AchievementEvent extends Event {

    @JsonProperty("achievement")
    private Achievement achievement;

    @JsonProperty("user")
    private EventUser user;
}

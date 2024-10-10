package osuapi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventUser {

    @JsonProperty("username")
    private String username;

    @JsonProperty("url")
    private String url;

    @JsonProperty("previousUsername")
    private String previousUsername;
}

package jospi.models.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventBeatmap {
    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;
}

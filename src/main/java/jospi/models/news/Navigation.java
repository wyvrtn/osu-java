package jospi.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Navigation {
    @JsonProperty("newer")
    private NewsPost newer;

    @JsonProperty("older")
    private NewsPost older;
}
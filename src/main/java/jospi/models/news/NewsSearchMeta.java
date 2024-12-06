package jospi.models.news;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsSearchMeta {

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("sort")
    private String sort;
}

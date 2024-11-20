package jospi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.matches.MatchBundleSort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchParams {

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("sort")
    private MatchBundleSort sort;
}

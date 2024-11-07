package osuapi.models.matches;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.matches.MatchBundleSort;

@Getter
@Setter
@NoArgsConstructor
public class MatchParams {

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("sort")
    private MatchBundleSort sort;
}

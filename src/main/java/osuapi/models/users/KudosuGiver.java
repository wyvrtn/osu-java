package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KudosuGiver {

    @JsonProperty("url")
    private String url;

    @JsonProperty("username")
    private String name;
}

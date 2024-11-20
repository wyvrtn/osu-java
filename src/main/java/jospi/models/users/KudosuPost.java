package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KudosuPost {
    
    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;
}

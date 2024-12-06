package jospi.models.changelogs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStream {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("id")
    private int id;

    @JsonProperty("is_featured")
    private boolean isFeatured;

    @JsonProperty("name")
    private String name;

    @JsonProperty("latest_build")
    private Build latestBuild;

    @JsonProperty("user_count")
    private int userCount;
}

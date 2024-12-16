package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Kudosu {
    @JsonProperty("available")
    private int available;

    @JsonProperty("total")
    private int total;
}

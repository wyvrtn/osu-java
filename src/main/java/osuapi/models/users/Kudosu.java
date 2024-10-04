package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Kudosu {
    @JsonProperty("available")
    public int available;

    @JsonProperty("total")
    public int total;
}

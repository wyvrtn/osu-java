package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NominationSummary {

	@JsonProperty("current")
	private int current;

	@JsonProperty("required")
	private int required;
}

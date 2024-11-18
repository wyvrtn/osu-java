package jospi.models.scores;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeightedPP {
	
	@JsonProperty("percentage")
	private float weight;
	
	@JsonProperty("pp")
	private float pp;
}

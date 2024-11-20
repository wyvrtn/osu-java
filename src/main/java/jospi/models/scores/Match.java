package jospi.models.scores;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match {
	
	@JsonProperty("pass")
	private boolean pass;
	
	@JsonProperty("slot")
	private int slot;
	
	@JsonProperty("team")
	private int team;
}

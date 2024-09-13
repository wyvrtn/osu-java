package osuapi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.Ruleset;

@Getter
@Setter
@NoArgsConstructor
public class Nomination {
	@JsonProperty("beatmapset_id")
	private int beatmapSetId;
	
	@JsonProperty("rulesets")
	private Ruleset[] rulesets;
	
	@JsonProperty("reset")
	private boolean reset;
	
	@JsonProperty("user_id")
	private int UserId;
}

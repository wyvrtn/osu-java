package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.enums.Ruleset;

@Getter
@Setter
@NoArgsConstructor
public class RankHistory {
	
	@JsonProperty("data")
	private int[] data;
	
	@JsonProperty("mode")
	private Ruleset ruleset;
}
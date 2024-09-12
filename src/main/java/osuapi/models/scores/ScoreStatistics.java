package osuapi.models.scores;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreStatistics {
	
	@JsonProperty("count_300")
	private int count300;
	
	@JsonProperty("count_100")
	private int count100;
	
	@JsonProperty("count_50")
	private int count50;
	
	@JsonProperty("count_geki")
	private int countGeki;
	
	@JsonProperty("count_katu")
	private int countKatu;
	
	@JsonProperty("count_miss")
	private int misses;
}

package osuapi.models.scores;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapScores {	
	
	@JsonProperty("scores")
	private Score[] scores;
	
	@JsonProperty("userScore")
	private UserBeatmapScore userScore;
}

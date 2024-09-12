package osuapi.models.users;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.enums.Grade;

@Getter
@Setter
@NoArgsConstructor
public class UserStatistics {
	
	@JsonProperty("count100")
	private int count100;
	
	@JsonProperty("count300")
	private int count300;
	
	@JsonProperty("count50")
	private int count50;
	
	@JsonProperty("count_miss")
	private int misses;
	
	@JsonProperty("country_rank")
	private int countryRank;
	
	@JsonProperty("global_rank")
	private int globalRank;
	
	@JsonProperty("global_rank_exp")
	private int globalRankExp;
	
	@JsonProperty("grade_counts")
	private Map<Grade, Integer> grades;
	
	@JsonProperty("hit_accuracy")
	private float accuracy;
	
	private boolean IsRanked;
	
	@JsonProperty("level")
	private Level level;
	
	@JsonProperty("maximum_combo")
	private int maxCombo;
	
	@JsonProperty("play_count")
	private int playCount;
	
	@JsonProperty("play_time")
	private int playTime;
	
	@JsonProperty("pp")
	private float pp;
	
	@JsonProperty("pp_exp")
	private float ppExp;
	
	@JsonProperty("ranked_score")
	private long rankedScore;
	
	@JsonProperty("total_score")
	private long totalScore;
}

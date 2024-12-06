package jospi.models.users;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.misc.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@JsonProperty("grade_counts")
	private Map<Grade, Integer> grades;

	@JsonProperty("hit_accuracy")
	private float accuracy;

	@JsonProperty("is_ranked")
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

	@JsonProperty("global_rank")
	private int globalRank;

	@JsonProperty("global_rank_exp")
	private int globalRankExp;

	@JsonProperty("ranked_score")
	private long rankedScore;

	@JsonProperty("replays_watched_by_others")
	private int replaysWatched;

	@JsonProperty("total_hits")
	private long totalHits;

	@JsonProperty("total_score")
	private long totalScore;

	// Optional Attributes

	@JsonProperty("rank_change_since_30_days")
	private int rankChangeSince30;

	@JsonProperty("user")
	private User user;
}

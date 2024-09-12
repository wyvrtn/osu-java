package osuapi.models.scores;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.beatmaps.BeatmapExtended;
import osuapi.models.beatmaps.BeatmapSet;
import osuapi.models.enums.Grade;
import osuapi.models.enums.Ruleset;
import osuapi.models.users.User;

@Getter
@Setter
@NoArgsConstructor
public class Score {
	
	@JsonProperty("accuracy")
	private float accuracy;
	
	@JsonProperty("best_id")
	private long bestId;
	
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("max_combo")
	private int maxCombo;
	
	@JsonProperty("mode")
	private Ruleset ruleset;
	
	@JsonProperty("mods")
	private String[] mods;
	
	@JsonProperty("passed")
	private boolean isPass;
	
	@JsonProperty("perfect")
	private boolean isPerfect;
	
	@JsonProperty("pp")
	private float pp;
	
	@JsonProperty("rank")
	private Grade grade;
	
	@JsonProperty("replay")
	private boolean isReplayAvailable;
	
	@JsonProperty("score")
	private int totalScore;
	
	private boolean isBest;
	
	@JsonProperty("statistics")
	private ScoreStatistics statistics;
	
	@JsonProperty("user_id")
	private int userId;
	
	/**
	 * Optional
	 */
	
	@JsonProperty("beatmap")
	private BeatmapExtended beatmap;
	
	@JsonProperty("beatmapset")
	private BeatmapSet beatmapSet;
	
	@JsonProperty("match")
	private Match match;
	
	@JsonProperty("rank_country")
	private int rankCountry;
	
	@JsonProperty("rank_global")
	private int rankGlobal;
	
	@JsonProperty("user")
	private User user;
	
	@JsonProperty("weight")
	private WeightedPP weightedPP;
}

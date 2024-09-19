package osuapi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import osuapi.enums.RankedStatus;
import osuapi.enums.Ruleset;
import osuapi.models.users.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Beatmap {
	/**
	 * Required Attributes
	 */
	
	@JsonProperty("beatmapset_id")
	private int setId;
	
	@JsonProperty("difficulty_rating")
	private float difficultyRating;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("mode")
	private Ruleset ruleset;
	
	@JsonProperty("status")
	private RankedStatus status;
	
	@JsonProperty("total_length")
	private int totalLength;
	
	@JsonProperty("user_id")
	private int userId;
	
	@JsonProperty("version")
	private String version;
	
	/**
	 * Optional Attributes
	 */
	
	@JsonProperty("beatmapset")
	private BeatmapSet set;
	
	@JsonProperty("checksum")
	private String checksum;
	
	@JsonProperty("failtimes")
	private Failtimes failtimes;
	
	@JsonProperty("max_combo")
	private int maxCombo;
	
	@JsonProperty("creator")
	private User creator;
}

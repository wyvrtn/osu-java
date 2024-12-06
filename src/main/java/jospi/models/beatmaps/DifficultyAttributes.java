package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DifficultyAttributes {

	@JsonProperty("max_combo")
	private int maxCombo;

	@JsonProperty("difficulty_rating")
	private float difficultyRating;

	@JsonProperty("aim_difficulty")
	private float aimDifficulty;

	@JsonProperty("approach_rate")
	private float AR;

	@JsonProperty("flashlight_difficulty")
	private float flashlightDifficulty;

	@JsonProperty("overall_difficulty")
	private float OD;

	@JsonProperty("slider_factor")
	private float sliderFactor;

	@JsonProperty("speed_difficulty")
	private float speedDifficulty;

	@JsonProperty("stamina_difficulty")
	private float staminaDifficulty;

	@JsonProperty("rhythm_difficulty")
	private float rhythmDifficulty;

	@JsonProperty("colour_difficulty")
	private float colourDifficulty;

	@JsonProperty("great_hit_window")
	private float greatHitWindow;
}

package jospi.models.beatmaps;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapExtended extends Beatmap {
	
	@JsonProperty("accuracy")
	private float overallDifficulty;
	
	@JsonProperty("ar")
	private float approachRate;
	
	@JsonProperty("drain")
	private float healthDrain;
	
	@JsonProperty("cs")
	private float circleSize;
	
	@JsonProperty("bpm")
	private float BPM;
	
	@JsonProperty("convert")
	private boolean convert;
	
	@JsonProperty("count_circles")
	private int circleCount;
	
	@JsonProperty("count_sliders")
	private int sliderCount;
	
	@JsonProperty("count_spinners")
	private int spinnerCount;
	
	private OffsetDateTime deletedAt;
	
	@JsonProperty("hit_length")
	private int hitLength;
	
	@JsonProperty("is_scoreable")
	private boolean isScoreable;
	
	@JsonProperty("last_updated")
	private OffsetDateTime lastUpdated;
	
	@JsonProperty("passcount")
	private int passCount;
	
	@JsonProperty("playcount")
	private int playCount;
	
	@JsonProperty("url")
	private String url;
	
	/**
	 * Optional
	 */
	private BeatmapSetExtended setExtended;
}

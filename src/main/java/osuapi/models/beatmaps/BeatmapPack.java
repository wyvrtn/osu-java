package osuapi.models.beatmaps;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapPack {
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("date")
	private OffsetDateTime createdAt;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("no_diff_reduction")
	private boolean noDifficultyReduction;

	@JsonProperty("ruleset_id")
	private int rulesetId;
	
	@JsonProperty("tag")
	private String tag;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("beatmapsets")
	private BeatmapSetExtended[] beatmapSets;
}

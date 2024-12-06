package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapSetCovers {

	@JsonProperty("cover")
	private String cover;

	@JsonProperty("cover@2x")
	private String cover2X;

	@JsonProperty("card")
	private String card;

	@JsonProperty("card@2x")
	private String card2X;

	@JsonProperty("list")
	private String list;

	@JsonProperty("list@2x")
	private String list2X;

	@JsonProperty("slimcover")
	private String slimCover;

	@JsonProperty("slimcover@2x")
	private String slimCover2X;
}

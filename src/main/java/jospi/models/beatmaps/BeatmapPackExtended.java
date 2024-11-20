package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapPackExtended extends BeatmapPack {
	
	@JsonProperty("beatmap_packs")
	private BeatmapPack[] beatmapPacks;
	
	@JsonProperty("cursor_string")
	private String cursorString;
}

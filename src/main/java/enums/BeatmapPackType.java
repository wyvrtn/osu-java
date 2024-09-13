package osuapi.models.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/** <summary>
 	An enum containing the type of beatmap packs that exist.
 	<br/><br/>
 	API docs: <a href="https://osu.ppy.sh/docs/index.html#beatmappacktype"/><br/>
 	Source: <a href="https://github.com/ppy/osu-web/blob/master/app/Models/BeatmapPack.php#L36"/>
 	</summary>
*/
public enum BeatmapPackType implements DescriptionEnum<BeatmapPackType> {
	STANDARD("standard"),
	FEATURED("featured"),
	TOURNAMENT("tournament"),
	LOVED("loved"),
	CHART("chart"),
	THEME("theme"),
	ARTIST("artist");
	
	private String description;
	
	private BeatmapPackType(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public BeatmapPackType getEnum(String input) {
		BeatmapPackType result = null;
		for (BeatmapPackType BeatmapPackType : values()) {
			if (StringUtils.equalsIgnoreCase(BeatmapPackType.name(), input) ||
					StringUtils.equalsIgnoreCase(BeatmapPackType.getDescription(), input)) {
				result = BeatmapPackType;
				break;
			}
		}
		return result;
	}
}

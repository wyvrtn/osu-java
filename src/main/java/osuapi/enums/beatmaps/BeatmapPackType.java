package osuapi.enums.beatmaps;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

/** <summary>
 	An enum containing the type of beatmap packs that exist.
 	<br/><br/>
 	API docs: <a href="https://osu.ppy.sh/docs/index.html#beatmappacktype"/><br/>
 	Source: <a href="https://github.com/ppy/osu-web/blob/master/app/Models/BeatmapPack.php#L36"/>
 	</summary>
*/
public enum BeatmapPackType implements DescriptionEnum {
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
}

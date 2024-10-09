package osuapi.enums.misc;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

/** <summary>
 	An enum containing the existing, official rulesets.
 	<br/><br/>
 	API docs: <a href="https://osu.ppy.sh/docs/index.html#ruleset"/><br/>
 	Source: <a href="https://github.com/ppy/osu-web/blob/master/app/Enums/Ruleset.php"/>
	</summary>
*/
public enum Ruleset implements DescriptionEnum<Ruleset> {
	OSU("osu"),
	TAIKO("taiko"),
	CATCH("fruits"),
	MANIA("mania");
	
	private String description;
	
	private Ruleset(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public Ruleset getEnum(String input) {
		Ruleset result = null;
		for (Ruleset Ruleset : values()) {
			if (StringUtils.equalsIgnoreCase(Ruleset.name(), input) ||
					StringUtils.equalsIgnoreCase(Ruleset.getDescription(), input)) {
				result = Ruleset;
				break;
			}
		}
		return result;
	}
}
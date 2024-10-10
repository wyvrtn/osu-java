package osuapi.enums.misc;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

/** <summary>
	An enum containing the grades a score can have. (XH, SH, X, S, A, B, C, D)
	<br/><br/>
	Source: <a href="https://github.com/ppy/osu-web/blob/master/resources/js/interfaces/rank.ts"/>
	</summary>
*/
public enum Grade implements DescriptionEnum<Grade> {
	//Self-explanatory, with "H" denoting presence of hidden and/or flashlight
	XH("ssh"),
	SH("sh"),
	X("ss"),
	S("s"),
	A("a"), 
	B("b"),
	C("c"),
	D("d");

	private String description;

	private Grade(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return this.description;
	}

	public Grade getEnum(String input) {
		Grade result = null;
		for (Grade Grade : values()) {
			if (StringUtils.equalsIgnoreCase(Grade.name(), input) ||
					StringUtils.equalsIgnoreCase(Grade.getDescription(), input)) {
				result = Grade;
				break;
			}
		}
		return result;
	}
}

package osuapi.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** <summary>
	An enum containing the grades a score can have. (XH, SH, X, S, A, B, C, D)
	<br/><br/>
	Source: <a href="https://github.com/ppy/osu-web/blob/master/resources/js/interfaces/rank.ts"/>
	</summary>
*/
public enum Grade {
	//Self-explanatory, with "H" denoting presence of hidden and/or flashlight
	XH, SH, X, S, A, B, C, D;
	
	@JsonCreator
	public static Grade fromString(String str) {
		Grade out;
		if ("SSH".equals(str)) out = XH;
		else out = Grade.valueOf(str);
		return out;
	}
	
	@Override
	@JsonValue
	public String toString() {
		return super.toString();
	}
}

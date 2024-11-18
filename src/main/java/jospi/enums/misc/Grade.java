package jospi.enums.misc;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum Grade implements DescriptionEnum {
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
}

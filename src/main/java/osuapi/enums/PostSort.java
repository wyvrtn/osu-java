package osuapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PostSort {
	IDASCENDING,
	IDDESCENDING;
	
	@Override
	@JsonValue
	public String toString() {
		return super.toString();
	}
}

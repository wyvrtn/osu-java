package jospi.enums.events;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum EventType implements DescriptionEnum {
	ACHIEVEMENT("achievement"),
	BEATMAPPLAYCOUNT("beatmapPlaycount"),
	BEATMAPSETAPPROVE("beatmapsetApprove"),
	BEATMAPSETDELETE("beatmapsetDelete"),
	BEATMAPSETREVIVE("beatmapsetRevive"),
	BEATMAPSETUPDATE("beatmapsetUpdate"),
	BEATMAPSETUPLOAD("beatmapsetUpload"),
    RANK("rank"),
    RANKLOST("rankLost"),
    USERSUPPORTAGAIN("userSupportAgain"),
    USERSUPPORTFIRST("userSupportFirst"),
    USERSUPPORTGIFT("userSupportGift"),
    USERNAMECHANGE("usernameChange");

	private String description;

	private EventType(String description) {
		this.description = description;
	}

	@JsonValue
	public String getDescription() {
		return this.description;
	}
}

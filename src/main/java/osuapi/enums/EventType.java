package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType implements DescriptionEnum<EventType> {
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
	
	public EventType getEnum(String input) {
		EventType result = null;
		for (EventType EventType : values()) {
			if (StringUtils.equalsIgnoreCase(EventType.name(), input) ||
					StringUtils.equalsIgnoreCase(EventType.getDescription(), input)) {
				result = EventType;
				break;
			}
		}
		return result;
	}
}

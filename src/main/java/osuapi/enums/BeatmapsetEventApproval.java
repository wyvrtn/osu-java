package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BeatmapsetEventApproval implements DescriptionEnum<BeatmapsetEventApproval> {
	RANKED("ranked"),
	APPROVED("approved"),
	QUALIFIED("qualified"),
	LOVED("loved");
	
	private String description;
	
	private BeatmapsetEventApproval(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public BeatmapsetEventApproval getEnum(String input) {
		BeatmapsetEventApproval result = null;
		for (BeatmapsetEventApproval BeatmapsetEventApproval : values()) {
			if (StringUtils.equalsIgnoreCase(BeatmapsetEventApproval.name(), input) ||
					StringUtils.equalsIgnoreCase(BeatmapsetEventApproval.getDescription(), input)) {
				result = BeatmapsetEventApproval;
				break;
			}
		}
		return result;
	}
}
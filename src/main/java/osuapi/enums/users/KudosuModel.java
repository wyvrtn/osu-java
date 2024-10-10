package osuapi.enums.users;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

import osuapi.enums.DescriptionEnum;

public enum KudosuModel implements DescriptionEnum<KudosuModel> {
	FORUM_POST("forum_post"),
	BEATMAP_DISCUSSION("beatmap_discussion");
	
	private String description;
	
	private KudosuModel(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public KudosuModel getEnum(String input) {
		KudosuModel result = null;
		for (KudosuModel KudosuModel : values()) {
			if (StringUtils.equalsIgnoreCase(KudosuModel.name(), input) ||
					StringUtils.equalsIgnoreCase(KudosuModel.getDescription(), input)) {
				result = KudosuModel;
				break;
			}
		}
		return result;
	}
}

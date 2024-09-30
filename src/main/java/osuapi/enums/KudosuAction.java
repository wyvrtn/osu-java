package osuapi.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KudosuAction implements DescriptionEnum<KudosuAction> {
	GIVE("give"),
	VOTE_GIVE("vote.give"),
	RESET("reset"),
	VOTE_RESET("vote.reset"),
    REVOKE("revoke"),
	VOTE_REVOKE("vote.revoke");
	
	private String description;
	
	private KudosuAction(String description) {
		this.description = description;
	}
	
	@JsonValue
	public String getDescription() {
		return this.description;
	}
	
	public KudosuAction getEnum(String input) {
		KudosuAction result = null;
		for (KudosuAction KudosuAction : values()) {
			if (StringUtils.equalsIgnoreCase(KudosuAction.name(), input) ||
					StringUtils.equalsIgnoreCase(KudosuAction.getDescription(), input)) {
				result = KudosuAction;
				break;
			}
		}
		return result;
	}
}

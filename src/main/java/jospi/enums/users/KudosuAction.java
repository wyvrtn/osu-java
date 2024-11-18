package jospi.enums.users;

import com.fasterxml.jackson.annotation.JsonValue;

import jospi.enums.DescriptionEnum;

public enum KudosuAction implements DescriptionEnum {
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
}

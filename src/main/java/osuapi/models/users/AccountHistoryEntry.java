package osuapi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.users.UserAccountHistoryEntryType;

// "UserAccountHistory" in documentation
@Getter
@Setter
@NoArgsConstructor
public class AccountHistoryEntry {

	// Not shown in documentation
	@JsonProperty("actor")
	private User user;

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	@JsonProperty("length")
	private int length;

	@JsonProperty("permanent")
	private boolean isPermanent;

	@JsonProperty("timestamp")
	private OffsetDateTime timestamp;

	@JsonProperty("type")
	private UserAccountHistoryEntryType type;
}

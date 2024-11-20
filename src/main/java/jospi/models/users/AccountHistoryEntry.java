package jospi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.users.UserAccountHistoryEntryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

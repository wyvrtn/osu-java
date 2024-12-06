package jospi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Achievement {

	@JsonProperty("achieved_at")
	private OffsetDateTime achievedAt;

	@JsonProperty("achievement_id")
	private int achievementId;
}

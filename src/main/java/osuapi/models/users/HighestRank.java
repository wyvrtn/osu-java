package osuapi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HighestRank {
	
	@JsonProperty("rank")
	private int rank;
	
	@JsonProperty("updated_at")
	public OffsetDateTime updatedAt;
}

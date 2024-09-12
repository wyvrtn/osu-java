package osuapi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyReplaysWatchedEntry {
	
	@JsonProperty("count")
	private int count;
	
	@JsonProperty("start_date")
	private OffsetDateTime startDate;
}

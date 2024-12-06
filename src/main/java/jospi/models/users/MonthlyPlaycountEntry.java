package jospi.models.users;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyPlaycountEntry {

	@JsonProperty("start_date")
	private LocalDate startDate;

	@JsonProperty("count")
	private int count;
}

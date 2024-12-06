package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Level {

	@JsonProperty("current")
	private int current;

	@JsonProperty("progress")
	private int progress;
}

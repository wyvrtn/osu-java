package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Failtimes {

	@JsonProperty("exit")
	private int[] exits;

	@JsonProperty("fail")
	private int[] fails;
}

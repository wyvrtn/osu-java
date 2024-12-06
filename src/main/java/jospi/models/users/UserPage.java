package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPage {

	@JsonProperty("html")
	private String html;

	@JsonProperty("raw")
	private String raw;
}

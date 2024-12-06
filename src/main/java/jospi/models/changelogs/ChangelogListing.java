package jospi.models.changelogs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangelogListing {

	@JsonProperty("builds")
	private Build[] builds;

	@JsonProperty("streams")
	private UpdateStream[] streams;
}

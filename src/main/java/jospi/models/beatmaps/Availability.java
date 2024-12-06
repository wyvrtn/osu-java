package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Availability {

	@JsonProperty("download_disabled")
	private boolean isDownloadDisabled;

	@JsonProperty("more_information")
	private String information;
}

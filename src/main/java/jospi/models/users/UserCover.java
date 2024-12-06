package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCover {

	@JsonProperty("custom_url")
	private String customUrl;

	@JsonProperty("id")
	private String id;

	@JsonProperty("url")
	private String url;
}

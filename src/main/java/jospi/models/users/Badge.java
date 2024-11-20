package jospi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Badge {
	
	@JsonProperty("awarded_at")
	private OffsetDateTime awardedAt;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("image@2x_url")
	private String image2X;
	
	@JsonProperty("image_url")
	private String image;
	
	@JsonProperty("url")
	private String url;
}

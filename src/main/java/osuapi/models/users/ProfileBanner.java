package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileBanner {
	
	@JsonProperty("id")
	private int id;
	
	private int tournamentId;
	
	@JsonProperty("image")
	private String image;
	
	@JsonProperty("image@2x")
	private String image2X;
}

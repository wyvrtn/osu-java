package osuapi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Country {
	
	@JsonProperty("code")
	private String code;
	
	private int display;
	
	@JsonProperty("name")
	private String name;
}

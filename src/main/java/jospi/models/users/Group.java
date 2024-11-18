package jospi.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Group {
	
	@JsonProperty("colour")
	private String colour;
	
	@JsonProperty("has_listing")
	private boolean hasListing;
	
	@JsonProperty("has_playmodes")
	private boolean hasPlaymodes;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("identifier")
	private String identifier;
	
	@JsonProperty("is_probationary")
	private boolean isProbationary;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("short_name")
	private String shortName;

	//Optional
	@JsonProperty("description")
	private GroupDescription description;
}

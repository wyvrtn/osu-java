package osuapi.models.forums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.generic.Text;

@Getter
@Setter
@NoArgsConstructor
public class PollOption {
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("text")
	private Text text;
	
	@JsonProperty("vote_count")
	private int voteCount;
}

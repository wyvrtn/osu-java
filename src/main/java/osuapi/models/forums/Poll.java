package osuapi.models.forums;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.Text;

@Getter
@Setter
@NoArgsConstructor
public class Poll {
	
	@JsonProperty("allow_vote_change")
	private boolean isVoteChangeAllowed;
	
	@JsonProperty("ended_at")
	private OffsetDateTime endedAt;
	
	@JsonProperty("hide_incomplete_results")
	private boolean hideIncompleteResults;
	
	@JsonProperty("last_vote_at")
	private OffsetDateTime lastVoteAt;
	
	@JsonProperty("max_votes")
	private int maxVotes;
	
	@JsonProperty("options")
	private PollOption[] options;
	
	@JsonProperty("started_at")
	private OffsetDateTime startedAt;
	
	@JsonProperty("title")
	private Text title;
	
	@JsonProperty("total_vote_count")
	private int totalVoteCount;
}

package osuapi.models.comments;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GitHubUser {
	
	@JsonProperty("display_name")
	private String displayName;
	
	@JsonProperty("github_url")
	private String gitHubUrl;
	
	@JsonProperty("github_username")
	private String gitHubUsername;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("osu_username")
	private String username;
	
	@JsonProperty("user_id")
	private int userId;
	
	@JsonProperty("user_url")
	private String url;
}

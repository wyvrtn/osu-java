package osuapi.models.changelogs;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.enums.ChangelogEntryCategory;
import osuapi.enums.ChangelogEntryType;
import osuapi.models.comments.GitHubUser;

@Getter
@Setter
@NoArgsConstructor
public class ChangelogEntry {
	
	@JsonProperty("category")
	private ChangelogEntryCategory category;
	
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty("github_pull_request_id")
	private int pullRequestId;
	
	@JsonProperty("github_url")
	private String pullRequestUrl;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("major")
	private boolean isMajor;
	
	@JsonProperty("repository")
	private String repository;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("type")
	private ChangelogEntryType type;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("github_user")
	private GitHubUser gitHubUser;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("message_html")
	private String messageHtml;
}

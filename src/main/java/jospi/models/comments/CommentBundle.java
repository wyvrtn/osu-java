package jospi.models.comments;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentBundle {
	public class Cursor {
		@JsonProperty("id")
		public int id;
		
		@JsonProperty("created_at")
		public OffsetDateTime createdAt;
	}
	
	@JsonProperty("cursor")
	Cursor cursor;
	
	@JsonProperty("commentable_meta")
	private CommentableMeta[] commentableMeta;
	
	@JsonProperty("comments")
	private Comment[] comments;
	
	@JsonProperty("has_more")
	private boolean hasMore;
	
	@JsonProperty("has_more_id")
	private int hasMoreId;
	
	@JsonProperty("included_comments")
	private Comment[] includedComments;
	
	@JsonProperty("pinned_comments")
	private Comment[] pinnedComments;
	
	@JsonProperty("sort")
	private String sort;
	
	@JsonProperty("top_level_count")
	private int topLevelCount;
	
	@JsonProperty("total")
	private int totalCount;
	
	@JsonProperty("user_follow")
	private boolean userFollow;
	
	@JsonProperty("user_votes")
	private int[] userVotes;
	
	@JsonProperty("users")
	private User[] users;
}

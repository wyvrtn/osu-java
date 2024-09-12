package osuapi.models.comments;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.enums.CommentableType;
import osuapi.models.users.User;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
	
	@JsonProperty("commentable_id")
	private int commentableId;
	
	@JsonProperty("commentable_type")
	private CommentableType commentableType;
	
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;
	
	@JsonProperty("deleted_by_id")
	private int deletedById;
	
	@JsonProperty("edited_at")
	private OffsetDateTime editedAt;
	
	@JsonProperty("edited_by_id")
	private int editedById;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("legacy_name")
	private String legacyName;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("message_html")
	private String messageHtml;
	
	@JsonProperty("parent_id")
	private int parentId;
	
	@JsonProperty("pinned")
	private boolean isPinned;
	
	@JsonProperty("replies_count")
	private int repliesCount;
	
	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;
	
	@JsonProperty("user")
	private User user;
	
	@JsonProperty("user_id")
	private int userId;
	
	@JsonProperty("votes_count")
	private int votesCount;
}

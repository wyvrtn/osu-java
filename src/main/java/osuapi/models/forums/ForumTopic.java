package osuapi.models.forums;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.models.enums.ForumTopicType;

@Getter
@Setter
@NoArgsConstructor
public class ForumTopic {
	
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;
	
	@JsonProperty("first_post_id")
	private int firstPostId;
	
	@JsonProperty("forum_id")
	private int forumId;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("is_locked")
	private boolean isLocked;
	
	@JsonProperty("poll")
	private Poll poll;
	
	@JsonProperty("post_count")
	private int postCount;
	
	private ForumPost[] posts;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("type")
	private ForumTopicType type;
	
	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;
	
	@JsonProperty("user_id")
	private int userId;
}

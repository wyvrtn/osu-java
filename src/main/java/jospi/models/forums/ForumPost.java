package jospi.models.forums;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.models.generic.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForumPost {
	
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	
	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;
	
	@JsonProperty("edited_at")
	private OffsetDateTime editedAt;
	
	@JsonProperty("edited_by_id")
	private int editedById;
	
	@JsonProperty("forum_id")
	private int forumId;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("topic_id")
	private int topicId;
	
	@JsonProperty("user_id")
	private int userId;
	
	@JsonProperty("body")
	private Text body;
}

package jospi.models.discussions;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiscussionPost {

	@JsonProperty("beatmapset_discussion_id")
	private int discussionId;

	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;

	@JsonProperty("deleted_by_id")
	private int deletedById;

	@JsonProperty("id")
	private int id;

	@JsonProperty("last_editor_id")
	private int lastEditorId;

	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;

	@JsonProperty("user_id")
	private int userId;

	/**
	 * Optional
	 */

	@JsonProperty("beatmap_discussion")
	private Discussion discussion;
}

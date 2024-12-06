package jospi.models.discussions;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.discussions.DiscussionType;
import jospi.models.beatmaps.Beatmap;
import jospi.models.beatmaps.BeatmapSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Discussion {

	@JsonProperty("beatmap_id")
	private int beatmapId;

	@JsonProperty("beatmapset_id")
	private int BeatmapSetId;

	@JsonProperty("can_be_resolved")
	private boolean canBeResolved;

	@JsonProperty("can_grant_kudosu")
	private boolean canGrantKudosu;

	@JsonProperty("created_at")
	private OffsetDateTime createdAt;

	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;

	@JsonProperty("deleted_by_id")
	private int deletedById;

	@JsonProperty("id")
	private int id;

	@JsonProperty("kudosu_denied")
	private boolean isKudosuDenied;

	@JsonProperty("last_post_at")
	private OffsetDateTime lastPostAt;

	@JsonProperty("message_type")
	private DiscussionType type;

	@JsonProperty("parent_id")
	private int parentId;

	@JsonProperty("resolved")
	private boolean isResolved;

	@JsonProperty("timestamp")
	private OffsetDateTime timestamp;

	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;

	@JsonProperty("user_id")
	private int userId;

	/**
	 * Optional
	 */

	@JsonProperty("beatmap")
	private Beatmap beatmap;

	@JsonProperty("beatmapset")
	private BeatmapSet beatmapSet;

	@JsonProperty("posts")
	private DiscussionPost[] posts;

	@JsonProperty("starting_post")
	private DiscussionPost startingPost;

	@JsonProperty("votes")
	private DiscussionVotes votes;
}

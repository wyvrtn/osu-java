package jospi.models.beatmaps;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapSetExtended extends BeatmapSet {

	@JsonProperty("bpm")
	private float bpm;

	@JsonProperty("can_be_hyped")
	private boolean canBeHyped;

	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;

	@JsonProperty("discussion_locked")
	private boolean isDiscussLocked;

	@JsonProperty("is_scoreable")
	private boolean isScoreable;

	@JsonProperty("last_updated")
	private OffsetDateTime lastUpdated;

	@JsonProperty("legacy_thread_url")
	private String legacyThreadUrl;

	@JsonProperty("nominations_summary")
	private NominationSummary nominationSummary;

	@JsonProperty("ranked_date")
	private OffsetDateTime rankedDate;

	@JsonProperty("storyboard")
	private boolean hasStoryboard;

	@JsonProperty("submitted_date")
	private OffsetDateTime submittedDate;

	@JsonProperty("tags")
	private String[] tags;

	/**
	 * Optional
	 */

	@JsonProperty("beatmaps")
	private BeatmapExtended[] beatmaps;
}

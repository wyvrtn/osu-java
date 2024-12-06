package jospi.models.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

import jospi.enums.beatmaps.RankedStatus;
import jospi.models.discussions.Discussion;
import jospi.models.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeatmapSet {

	@JsonProperty("artist")
	private String artist;

	@JsonProperty("artist_unicode")
	private String artistUnicode;

	@JsonProperty("covers")
	private BeatmapSetCovers covers;

	@JsonProperty("creator")
	private String creatorName;

	@JsonProperty("favorite_count")
	private int favoriteCount;

	@JsonProperty("hype")
	private Hypes hypes;

	@JsonProperty("id")
	private int id;

	private boolean isNsfw;

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("play_count")
	private int playCount;

	@JsonProperty("preview_url")
	private String previewUrl;

	@JsonProperty("source")
	private String source;

	@JsonProperty("spotlight")
	private boolean isSpotlight;

	@JsonProperty("status")
	private RankedStatus status;

	@JsonProperty("title")
	private String title;

	@JsonProperty("title_unicode")
	private String titleUnicode;

	@JsonProperty("track_id")
	private int trackId;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("video")
	private boolean hasVideo;

	/**
	 * Optional
	 */

	@JsonProperty("availability")
	private Availability avail;

	@JsonProperty("beatmaps")
	private Beatmap[] beatmaps;

	@JsonProperty("converts")
	private BeatmapExtended[] converts;

	@JsonProperty("current_nominations")
	private Nomination[] nominations;

	@JsonProperty("description")
	private BeatmapSetDescription description;

	@JsonProperty("discussions")
	private Discussion[] discussions;

	@JsonProperty("events")
	private BeatmapSetEvent[] events;

	@JsonProperty("genre")
	private Genre genre;

	@JsonProperty("language")
	private Language lang;

	@JsonProperty("ratings")
	private int[] ratings;

	@JsonProperty("recent_favourites")
	private User[] recentFavorites;

	@JsonProperty("related_users")
	private User[] relatedUsers;

	@JsonProperty("user")
	private User creator;
}

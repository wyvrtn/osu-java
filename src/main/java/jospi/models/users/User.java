package jospi.models.users;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("default_group")
    private String defaultGroup;

    @JsonProperty("id")
    private int id;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("is_bot")
    private boolean isBot;

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    @JsonProperty("is_online")
    private boolean isOnline;

    @JsonProperty("is_supporter")
    private boolean isSupporter;

    @JsonProperty("last_visit")
    private OffsetDateTime lastVisit;

    @JsonProperty("pm_friends_only")
    private boolean isPMFriendsOnly;

    @JsonProperty("profile_colour")
    private String profileColour;

    @JsonProperty("username")
    private String username;

    /**
    * Optional
    */

    @JsonProperty("account_history")
    private AccountHistoryEntry accountHistory;

    @JsonProperty("active_tournament_banners")
    private ProfileBanner[] banners;

    @JsonProperty("badges")
    private Badge[] badges;

    @JsonProperty("beatmap_playcounts_count")
    private int mostPlayedCount;

    //JsonProperty "blocks", type not found

    //Seems to be unavailable in documentation
    @JsonProperty("comments_count")
    private int commentsCount;

    @JsonProperty("country")
    private Country country;

    @JsonProperty("cover")
    private UserCover cover;

    @JsonProperty("favourite_beatmapset_count")
    private int favouriteBeatmapSetsCount;

    @JsonProperty("follow_user_mapping")
    private int[] followUserMapping;

    @JsonProperty("follower_count")
    private int followerCount;

    //JsonProperty "friends", type not found

    @JsonProperty("graveyard_beatmapset_count")
    private int graveyardedBeatmapSetsCount;

    @JsonProperty("groups")
    private UserGroup[] groups;

    @JsonProperty("guest_beatmapset_count")
    private int guestBeatmapSetsCount;

    @JsonProperty("is_admin")
    private boolean isAdmin;

    @JsonProperty("is_bng")
    private boolean isBNG;

    @JsonProperty("is_full_bn")
    private boolean isFullNominator;

    @JsonProperty("is_gmt")
    private boolean isGMT;

    @JsonProperty("is_limited_bn")
    private boolean isProbationaryNominator;

    @JsonProperty("is_moderator")
    private boolean isModerator;

    @JsonProperty("is_nat")
    private boolean isNAT;

    // Only boolean shown in documentation from "guest_beatmap_count" to "kudosu"
    @JsonProperty("is_restricted")
    private boolean isRestricted;

    @JsonProperty("is_silenced")
    private boolean isSilenced;

    @JsonProperty("kudosu")
    private Kudosu kudosu;

    @JsonProperty("loved_beatmapset_count")
    private int lovedBeatmapSetsCount;

    @JsonProperty("mapping_follower_count")
    private int mappingFollowerCount;

    @JsonProperty("monthly_playcounts")
    private MonthlyPlaycountEntry[] monthlyPlaycount;

    @JsonProperty("nominated_beatmapset_count")
    private int nominatedBeatmapSetsCount;

    @JsonProperty("page")
    private UserPage page;

    @JsonProperty("pending_beatmapset_count")
    private int pendingBeatmapSetsCount;

    @JsonProperty("previous_usernames")
    private String[] previousUsernames;

    @JsonProperty("rank_highest")
    private HighestRank highestRank;

    @JsonProperty("rank_history")
    private RankHistory rankHistory;

    @JsonProperty("ranked_beatmapset_count")
    private int rankedBeatmapSetsCount;

    @JsonProperty("replays_watched_counts")
    private MonthlyReplaysWatchedEntry[] replaysWatched;

    @JsonProperty("scores_best_count")
    private int bestScoresCount;

    @JsonProperty("scores_first_count")
    private int firstScoresCount;

    @JsonProperty("scores_pinned_count")
    private int pinnedScoresCount;

    @JsonProperty("scores_recent_count")
    private int recentScoresCount;

    @JsonProperty("session_verified")
    private boolean isSessionVerified;

    @JsonProperty("statistics")
    private UserStatistics statistics;

    @JsonProperty("support_level")
    private int supportLevel;

    @JsonProperty("user_achievements")
    private Achievement[] achievements;
}

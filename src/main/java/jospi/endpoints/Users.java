package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.client.resources.ClientUtil;
import jospi.enums.beatmaps.BeatmapType;
import jospi.enums.misc.Ruleset;
import jospi.enums.users.UserScoreType;
import jospi.models.beatmaps.BeatmapSetExtended;
import jospi.models.records.UserResultParams;
import jospi.models.scores.Score;
import jospi.models.users.BeatmapPlaycount;
import jospi.models.users.KudosuHistoryEntry;
import jospi.models.users.User;
import jospi.models.users.UserExtended;

public final class Users {
    private static final String BASE = "/users/";

    private final OsuApiClient client;

    protected Users(OsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<UserExtended> getOwnData() {
        return getOwnData(Ruleset.OSU);
    }

    public CompletableFuture<UserExtended> getOwnData(Ruleset ruleset) {
        client.requiresUser();
        return client.getJsonAsync("/me/" + ClientUtil.nullishCoalesce(ruleset, ""), UserExtended.class);
    }

    public CompletableFuture<KudosuHistoryEntry[]> getKudosuHistory(int userId, UserResultParams params) {
        return client.getJsonAsync(BASE + userId + "/kudosu", params.convert(), KudosuHistoryEntry[].class);
    }

    public CompletableFuture<Score[]> getUserScores(int userId, UserScoreType type, boolean legacyOnly,
        boolean includeFails, Ruleset ruleset, UserResultParams usrParams) {
        return client.getJsonAsync(BASE + userId + "/scores/" + type.getDescription(), map -> {
                map.put("legacy_only", legacyOnly);
                map.put("include_fails", includeFails);
                map.put("mode", ruleset);
                map.put("", usrParams);
            }, Score[].class);
    }

    public CompletableFuture<BeatmapPlaycount[]> getUserMostPlayed(int userId, UserResultParams params) {
        return client.getJsonAsync(BASE + userId + "/beatmapsets/" + BeatmapType.MOST_PLAYED.getDescription(), params.convert(), BeatmapPlaycount[].class);
    }

    public CompletableFuture<BeatmapSetExtended[]> getUserBeatmaps(int userId, BeatmapType type, UserResultParams params) {
        if (type==BeatmapType.MOST_PLAYED) {
            throw new IllegalArgumentException("Please use GetUserMostPlayed(), as the response type differs.");
        }
        return client.getJsonAsync(BASE + userId + "/beatmapsets/" + type.getDescription(), params.convert(), BeatmapSetExtended[].class);
    }

    public CompletableFuture<User> getUser(int userId, Ruleset ruleset) {
        return getUserInternal(Integer.toString(userId), ruleset);
    }

    public CompletableFuture<User> getUser(String username, Ruleset ruleset) {
        return getUserInternal("@" + username, ruleset);
    }

    private CompletableFuture<User> getUserInternal(String userIdentifier, Ruleset ruleset) {
        return client.getJsonAsync(BASE + userIdentifier + "/" + ClientUtil.nullishCoalesce(ruleset, ""), User.class);
    }

    public CompletableFuture<User[]> getUsers(int[] ids, boolean includeVariantStatistics) {
        if (ids.length > 50) {
            throw new IndexOutOfBoundsException("Parameter 'ids' can only have an array length of 50 or less");
        }
        return client.getJsonAsync(BASE, map -> {
                map.put("include_variant_statistics", includeVariantStatistics);
                for (int id : ids) {
                    map.put("ids[]", id);
                }
            }, User[].class);
    }
}

package jospi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.client.resources.ClientUtil;
import jospi.enums.beatmaps.BeatmapType;
import jospi.enums.misc.Ruleset;
import jospi.enums.users.UserScoreType;
import jospi.models.beatmaps.BeatmapSetExtended;
import jospi.models.scores.Score;
import jospi.models.users.BeatmapPlaycount;
import jospi.models.users.KudosuHistoryEntry;
import jospi.models.users.User;
import jospi.models.users.UserExtended;

public final class Users {
    private static final String BASE = "/users/";
	
	private OsuApiClient client;

	protected Users(OsuApiClient client) {
		this.client = client;
	}

	public CompletableFuture<UserExtended> getOwnData() {
		return getOwnData(null);
	}

	public CompletableFuture<UserExtended> getOwnData(Ruleset ruleset) {
		client.requiresUser();
		return client.getJsonAsync("/me/"+ClientUtil.nullishCoalesce(ruleset, ""));
	}

	public CompletableFuture<KudosuHistoryEntry[]> getKudosuHistory(int userId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+userId+"/kudosu", params)
		);
	}

	public CompletableFuture<Score[]> getUserScores(int userId, UserScoreType type, boolean legacyOnly, 
		boolean includeFails, Ruleset ruleset, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		params.put("legacy_only", legacyOnly);
		params.put("include_fails", includeFails);
		params.put("mode", ruleset);
		params.put("limit", limit);
		params.put("offset", offset);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+userId+"/scores/"+type.getDescription(), params)
		);
	}

	public CompletableFuture<BeatmapPlaycount[]> getUserMostPlayed(int userId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+userId+"/beatmapsets/"+BeatmapType.MOST_PLAYED.getDescription(), params)
		);
	}

	public CompletableFuture<BeatmapSetExtended[]> getUserBeatmaps(int userId, BeatmapType type, int limit, int offset) {
		if (type==BeatmapType.MOST_PLAYED) {
			throw new IllegalArgumentException("Please use GetUserMostPlayed(), as the response type differs.");
		}
		Map<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("offset", offset);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+userId+"/beatmapsets/"+type.getDescription(), params)
		);
	}

	public CompletableFuture<User> getUser(int userId, Ruleset ruleset) {
		return getUserInternal(Integer.toString(userId), ruleset);
	}

	public CompletableFuture<User> getUser(String username, Ruleset ruleset) {
		return getUserInternal("@"+username, ruleset);
	}

	private CompletableFuture<User> getUserInternal(String userIdentifier, Ruleset ruleset) {
		return client.getJsonAsync(BASE+userIdentifier+"/"+ClientUtil.nullishCoalesce(ruleset, ""));
	}

	public CompletableFuture<User[]> getUsers(int[] ids, boolean includeVariantStatistics) {
		if (ids.length>50) {
			throw new IndexOutOfBoundsException("Parameter 'ids' can only have an array length of 50 or less");
		}
		return CompletableFuture.supplyAsync(() -> {
			Map<String, Object> params = new HashMap<>();
			params.put("include_variant_statistics", includeVariantStatistics);
			for (int id : ids) params.put("ids[]", id);
			return client.getJson(BASE, params);
		});
	}
}

package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.client.resources.ClientUtil;
import osuapi.enums.BeatmapType;
import osuapi.enums.Ruleset;
import osuapi.enums.UserScoreType;
import osuapi.models.beatmaps.BeatmapSetExtended;
import osuapi.models.scores.Score;
import osuapi.models.users.BeatmapPlaycount;
import osuapi.models.users.KudosuHistoryEntry;
import osuapi.models.users.User;

public final class Users {
    private static final String BASE = "/users/";
	
	private OsuApiClient client;

	protected Users(OsuApiClient client) {
		this.client = client;
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
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+userIdentifier+"/"+ClientUtil.nullishCoalesce(ruleset, ""))
		);
	}

	public CompletableFuture<User[]> getUsers(int[] ids, boolean includeVariantStatistics) {
		if (ids.length>50) {
			throw new IndexOutOfBoundsException(ids.length);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("include_variant_statistics", includeVariantStatistics);
		for (int id : ids) {
			params.put("ids[]", id);
		}
		return client.getJson(BASE, params);
	}
}

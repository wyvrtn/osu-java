package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import osuapi.client.core.OsuApiClient;
import osuapi.client.resources.ClientUtil;
import osuapi.enums.beatmaps.BeatmapType;
import osuapi.enums.misc.Ruleset;
import osuapi.enums.users.UserScoreType;
import osuapi.models.beatmaps.BeatmapSetExtended;
import osuapi.models.scores.Score;
import osuapi.models.structs.UserParameters;
import osuapi.models.structs.processors.AnonymousStructProcessor;
import osuapi.models.users.BeatmapPlaycount;
import osuapi.models.users.KudosuHistoryEntry;
import osuapi.models.users.User;
import osuapi.models.users.UserExtended;

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

	public <T extends UserParameters> CompletableFuture<KudosuHistoryEntry[]> getKudosuHistory(int userId, T params) {
		return client.getJsonAsync(BASE+userId+"/kudosu", ClientUtil.buildQueryMap(params, AnonymousStructProcessor.class));
	}

	public <T extends UserParameters> CompletableFuture<Score[]> getUserScores(int userId, UserScoreType type, boolean legacyOnly, 
		boolean includeFails, Ruleset ruleset, T userParams) {
		return CompletableFuture.supplyAsync(() -> {
			Map<String, Object> params = new HashMap<>();
			params.put("legacy_only", legacyOnly);
			params.put("include_fails", includeFails);
			params.put("mode", ruleset);
			params.putAll(ClientUtil.buildQueryMap(userParams, AnonymousStructProcessor.class));
			return client.getJson(BASE+userId+"/scores/"+type.getDescription(), params);
		});
	}

	public <T extends UserParameters> CompletableFuture<BeatmapPlaycount[]> getUserMostPlayed(int userId, T params) {
		return client.getJsonAsync(BASE+userId+"/beatmapsets/"+BeatmapType.MOST_PLAYED.getDescription(), ClientUtil.buildQueryMap(params, AnonymousStructProcessor.class));
		
	}

	public <T extends UserParameters> CompletableFuture<BeatmapSetExtended[]> getUserBeatmaps(int userId, BeatmapType type, T params) {
		if (type==BeatmapType.MOST_PLAYED) {
			throw new IllegalArgumentException("Please use GetUserMostPlayed(), as the response type differs.");
		}
		return client.getJsonAsync(BASE+userId+"/beatmapsets/"+type.getDescription(), ClientUtil.buildQueryMap(params, AnonymousStructProcessor.class));
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

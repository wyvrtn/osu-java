package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.enums.Ruleset;
import osuapi.enums.UserScoreType;
import osuapi.models.scores.Score;
import osuapi.models.users.BeatmapPlaycount;
import osuapi.models.users.KudosuHistoryEntry;

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
			client.getJson(BASE+userId+"/beatmapsets/most_played", params)
		);
	}

	
}

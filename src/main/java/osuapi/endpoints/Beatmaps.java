package osuapi.endpoints;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;

import osuapi.client.OsuApiClient;
import osuapi.enums.Ruleset;
import osuapi.models.beatmaps.Beatmap;
import osuapi.models.beatmaps.BeatmapExtended;
import osuapi.models.beatmaps.DifficultyAttributes;
import osuapi.models.scores.BeatmapScores;
import osuapi.models.scores.Score;
import osuapi.models.scores.UserBeatmapScore;

public final class Beatmaps {
	private static final String BEATMAPS_BASE = "/beatmaps/";

	private OsuApiClient client; 

	public Beatmaps(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<Beatmap> lookupBeatmapChecksum(String checksum) {
		try {
			return lookupBeatmapInternal("checksum=" + URLEncoder.encode(checksum, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(new Beatmap());
	}
	
	public CompletableFuture<Beatmap> lookupBeatmapFilename(String filename) {
		return lookupBeatmapInternal("filename=" + filename);
	}
	
	public CompletableFuture<Beatmap> lookupBeatmapId(String id) {
		return lookupBeatmapInternal("id=" + id);
	}
	
	private CompletableFuture<Beatmap> lookupBeatmapInternal(String query) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BEATMAPS_BASE+"lookup?" + query, new Beatmap())
		);
	}
	
	public CompletableFuture<UserBeatmapScore> getUserBeatmapScore(int beatmapId, int userId, Ruleset ruleset, String mods) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		params.put("mods", mods);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BEATMAPS_BASE+beatmapId+"/scores/users/"
				+userId, params, new UserBeatmapScore())
		);
	}
	
	public CompletableFuture<Score[]> getUserBeatmapScores(int beatmapId, int userId, Ruleset ruleset) {
		Map<String, Object> params = new HashMap<>();
		params.put("ruleset", ruleset);
		return CompletableFuture.supplyAsync(() -> 
			 client.getJson(BEATMAPS_BASE+beatmapId+"/scores/users/"
				+userId+"/all", params, new Score[Integer.MAX_VALUE])
		);
	}
	
	public CompletableFuture<BeatmapScores> getBeatmapScores(int beatmapId, Ruleset ruleset, String mods) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		params.put("mods", mods);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BEATMAPS_BASE+beatmapId+"/scores", params, new BeatmapScores())
		);
	}
	
	public CompletableFuture<BeatmapExtended[]> getBeatmaps(int[] ids) {
		Map<String, Object> params = new HashMap<>();
		for (int id : ids) params.put("ids[]", id);
		return CompletableFuture.supplyAsync(() -> 
		client.getJson("/beatmaps", params, new BeatmapExtended[50])
				);
	}
	
	public CompletableFuture<BeatmapExtended> getBeatmap(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BEATMAPS_BASE+id, new BeatmapExtended())
		);
	}
	
	public CompletableFuture<DifficultyAttributes> getDifficultyAttributes(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BEATMAPS_BASE+id+"/attributes", new DifficultyAttributes(), HttpMethod.POST)
		);
	}
}

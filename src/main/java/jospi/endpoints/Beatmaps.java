package jospi.endpoints;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpMethod;

import jospi.client.core.OsuApiClient;
import jospi.enums.misc.Ruleset;
import jospi.models.beatmaps.Beatmap;
import jospi.models.beatmaps.BeatmapExtended;
import jospi.models.beatmaps.DifficultyAttributes;
import jospi.models.scores.BeatmapScores;
import jospi.models.scores.Score;
import jospi.models.scores.UserBeatmapScore;

public final class Beatmaps {
	private static final String BASE = "/beatmaps/";

	private final OsuApiClient client; 

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
			client.getJson(BASE+"lookup?" + query)
		);
	}
	
	public CompletableFuture<UserBeatmapScore> getUserBeatmapScore(int beatmapId, int userId, Ruleset ruleset, String mods) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		params.put("mods", mods);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+beatmapId+"/scores/users/"
				+userId, params)
		);
	}
	
	public CompletableFuture<Score[]> getUserBeatmapScores(int beatmapId, int userId, Ruleset ruleset) {
		Map<String, Object> params = new HashMap<>();
		params.put("ruleset", ruleset);
		return CompletableFuture.supplyAsync(() -> 
			 client.getJson(BASE+beatmapId+"/scores/users/"
				+userId+"/all", params)
		);
	}
	
	public CompletableFuture<BeatmapScores> getBeatmapScores(int beatmapId, Ruleset ruleset, String mods) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		params.put("mods", mods);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+beatmapId+"/scores", params)
		);
	}
	
	public CompletableFuture<BeatmapExtended[]> getBeatmaps(int[] ids) {
		Map<String, Object> params = new HashMap<>();
		for (int id : ids) params.put("ids[]", id);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/beatmaps", params)
		);
	}
	
	public CompletableFuture<BeatmapExtended> getBeatmap(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+id)
		);
	}
	
	public CompletableFuture<DifficultyAttributes> getDifficultyAttributes(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+id+"/attributes", HttpMethod.POST)
		);
	}
}

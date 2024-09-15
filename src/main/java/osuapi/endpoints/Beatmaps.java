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
import osuapi.models.scores.Score;
import osuapi.models.scores.UserBeatmapScore;

public final class Beatmaps {

	private OsuApiClient client; 

	protected Beatmaps(OsuApiClient client) {
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
			client.getJson("/beatmaps/lookup?" + query, new Beatmap())
		);
	}
	
	public CompletableFuture<UserBeatmapScore> getUserBeatmapScore(int beatmapId, int userId, Ruleset ruleset, String mods) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		params.put("mods", mods);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/beatmaps/"+beatmapId+"/scores/users/"
				+userId+client.buildQueryString(params), new UserBeatmapScore())
		);
	}
	
	public CompletableFuture<Score[]> getUserBeatmapScores(int beatmapId, int userId, Ruleset ruleset) {
		Map<String, Object> params = new HashMap<>();
		params.put("mode", ruleset);
		return CompletableFuture.supplyAsync(() -> 
			 client.getJson("/beatmaps/"+beatmapId+"/scores/users/"
				+userId+"/all"+client.buildQueryString(params), new Score[Integer.MAX_VALUE])
		);
	}
	
	public List<CompletableFuture<BeatmapExtended>> getBeatmaps(int[] ids) {
		return Arrays.stream(ids.length>50? Arrays.copyOf(ids, 50) : ids).boxed().map(this::getBeatmap).collect(Collectors.toList());
	}
	
	public CompletableFuture<BeatmapExtended> getBeatmap(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/beatmaps/"+id, new BeatmapExtended())
		);
	}
	
	public CompletableFuture<DifficultyAttributes> getDifficultyAttributes(int id) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/beatmaps/"+id+"/attributes", new DifficultyAttributes(), HttpMethod.POST)
		);
	}
}

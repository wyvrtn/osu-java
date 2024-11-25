package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.models.beatmaps.BeatmapSetExtended;

public final class BeatmapSets {
	private static final String BASE = "/beatmapsets/";
	
	private final OsuApiClient client;

	protected BeatmapSets(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<BeatmapSetExtended> lookupBeatmapSet(int beatmapId) {
		return client.getJsonAsync(BASE+"lookup?beatmap_id="+beatmapId);
	}
	
	public CompletableFuture<BeatmapSetExtended> getBeatmapSet(int beatmapSetId) {
		return client.getJsonAsync(BASE+beatmapSetId);
	}
}

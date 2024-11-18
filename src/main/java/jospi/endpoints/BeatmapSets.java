package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.models.beatmaps.BeatmapSetExtended;

//API docs: https://osu.ppy.sh/docs/index.html#beatmapsets
public final class BeatmapSets {
	private static final String BASE = "/beatmapsets/";
	
	private OsuApiClient client;

	protected BeatmapSets(OsuApiClient client) {
		this.client = client;
	}
	
	/// <summary>
	/// Looks up the beatmapset that contains the beatmap with the specified ID. If the beatmapset was not found, null is returned.
	/// <br/><br/>
	/// API notes:<br/>
	/// <a href="https://osu.ppy.sh/docs/index.html#get-apiv2beatmapsetslookup"/>
	/// </summary>
	/// <param name="beatmapId">The ID of the beatmap.</param>
	/// <returns>The beatmapset or null, if the beatmapset was not found.</returns>
	public CompletableFuture<BeatmapSetExtended> lookupBeatmapSet(int beatmapId) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+"lookup?beatmap_id="+beatmapId)
		);
	}
	
	public CompletableFuture<BeatmapSetExtended> getBeatmapSet(int beatmapSetId) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+beatmapSetId)
		);
	}
}

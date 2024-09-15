package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.models.beatmaps.BeatmapSetExtended;

//API docs: https://osu.ppy.sh/docs/index.html#beatmapsets
public final class BeatmapSets {
	
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
			client.getJson("/beatmapsets/lookup?beatmap_id="+beatmapId,
				new BeatmapSetExtended())
		);
	}
	
	public CompletableFuture<BeatmapSetExtended> getBeatmapSet(int beatmapSetId) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/beatmapsets/"+beatmapSetId,
				new BeatmapSetExtended())
		);
	}
}

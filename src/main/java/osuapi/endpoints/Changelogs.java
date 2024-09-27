package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.models.changelogs.Build;
import osuapi.models.changelogs.ChangelogListing;

public final class Changelogs {
	private static final String BASE = "/changelog/";
	
	private OsuApiClient client;

	protected Changelogs(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<Build> getBuild(String stream, String build) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+stream+"/"+build)
		);
	}
	
	public CompletableFuture<ChangelogListing> getChangelogListing(String stream, String fromBuild, String toBuild, int maxBuildId) {
		Map<String, Object> params = new HashMap<>();
		params.put("stream", stream);
		params.put("from", fromBuild);
		params.put("to", toBuild);
		params.put("max_id", maxBuildId);
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/changelog", params)
		);
	}
	
	public CompletableFuture<Build> lookupBuildId(int buildId) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson(BASE+buildId+"?key=id")
		);
	}
	
	public CompletableFuture<Build> lookupLatestBuild(String stream) {
		return CompletableFuture.supplyAsync(() -> 
				client.getJson(BASE+stream)
		);
	}
}

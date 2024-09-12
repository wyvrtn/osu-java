package osuapi.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import osuapi.client.OsuApiClient;
import osuapi.framework.exception.OsuApiException;
import osuapi.models.changelogs.Build;
import osuapi.models.changelogs.ChangelogListing;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Changelogs {
	
	@Autowired
	private OsuApiClient client;
	
	public CompletableFuture<Build> getBuild(String stream, String build) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/changelog/"+stream+"/"+build, new Build());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	public CompletableFuture<ChangelogListing> getChangelogListing(String stream, String fromBuild, String toBuild, int maxBuildId) {
		Map<String, Object> params = new HashMap<>();
		params.put("stream", stream);
		params.put("from", fromBuild);
		params.put("to", toBuild);
		params.put("max_id", maxBuildId);
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/changelog"+client.buildQueryString(params), new ChangelogListing());
			} catch (OsuApiException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	public CompletableFuture<Build> lookupBuildId(int buildId) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/changelog/"+buildId+"?key=id", new Build());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
	
	public CompletableFuture<Build> lookupLatestBuild(String stream) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/changelog/"+stream, new Build());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}

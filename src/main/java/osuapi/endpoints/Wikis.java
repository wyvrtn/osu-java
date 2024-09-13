package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.framework.exception.OsuApiException;
import osuapi.models.wikis.WikiPage;

public final class Wikis {
	
	private OsuApiClient client;

	protected Wikis(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<WikiPage> getWikiPage(String locale, String path) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/wiki/"+locale+"/"+path, new WikiPage());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}

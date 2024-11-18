package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.models.wikis.WikiPage;

public final class Wikis {
	
	private OsuApiClient client;

	protected Wikis(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<WikiPage> getWikiPage(String locale, String path) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/wiki/"+locale+"/"+path)
		);
	}
}

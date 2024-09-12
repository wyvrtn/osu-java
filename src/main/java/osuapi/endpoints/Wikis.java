package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import osuapi.client.OsuApiClient;
import osuapi.models.custom.OsuApiException;
import osuapi.models.wikis.WikiPage;

public class Wikis {
	
	@Autowired
	private OsuApiClient client;
	
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

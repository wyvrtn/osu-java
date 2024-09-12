package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import osuapi.client.OsuApiClient;
import osuapi.framework.exception.OsuApiException;
import osuapi.models.wikis.WikiPage;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Wikis {
	
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

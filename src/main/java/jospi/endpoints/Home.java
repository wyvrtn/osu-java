package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.enums.home.HomeSearchQueryMode;
import jospi.models.home.SearchResult;
import jospi.models.users.User;
import jospi.models.wikis.WikiPage;

public class Home {
    private static final String BASE = "/search/";

	private final OsuApiClient client;

	protected Home(OsuApiClient client) {
		this.client = client;
	}

    public CompletableFuture<SearchResult<User>> searchUser(String query) {
        return searchInternal(HomeSearchQueryMode.USER, query);
    }

    public CompletableFuture<SearchResult<WikiPage>> searchWiki(String query) {
        return searchInternal(HomeSearchQueryMode.WIKIPAGE, query);
    }

    public CompletableFuture<SearchResult<Object>> search(String query) {
        return searchInternal(HomeSearchQueryMode.ALL, query);
    }

    private <T> CompletableFuture<SearchResult<T>> searchInternal(HomeSearchQueryMode mode, String query) {
        return client.getJsonAsync(BASE, map -> {
            map.put("mode", mode);
            map.put("query", query);
        });
    }
}

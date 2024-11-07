package osuapi.endpoints;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import osuapi.client.core.OsuApiClient;
import osuapi.client.resources.ClientUtil;
import osuapi.enums.home.HomeSearchQueryMode;
import osuapi.models.home.SearchResult;
import osuapi.models.users.User;
import osuapi.models.wikis.WikiPage;

public class Home {
    private static final String BASE = "/search/";
    	
	private OsuApiClient client;

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
        return client.getJsonAsync(BASE, ClientUtil.buildQueryMap(Arrays.asList("mode", "query"), mode, query));
    }
}

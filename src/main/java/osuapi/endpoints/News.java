package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.OsuApiClient;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.news.NewsBundle;
import osuapi.models.news.NewsPost;

public final class News {
    private static final String BASE = "/news/";
	
	private OsuApiClient client;

	protected News(OsuApiClient client) {
		this.client = client;
	}

    public AsyncLazyEnumerable<String, NewsPost[]> getNewsPosts() {
        ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<NewsPost[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				Map<String, Object> params = new HashMap<>();
				params.put("cursor_string", token.getToken());
				NewsBundle bundle = client.getJson(BASE, params);
				token.setNext(bundle.getCursorString());
				return bundle.getNewsPosts();
			});
		return new AsyncLazyEnumerable<>(func, token);
    }

    public CompletableFuture<NewsPost> getNewsPost(String slug) {
        return CompletableFuture.supplyAsync(() ->
            client.getJson(BASE+"slug")
        );
    }

	public CompletableFuture<NewsPost> getNewsPost(int id) {
		return getNewsPost(Integer.toString(id));
	}
}

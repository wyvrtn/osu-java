package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.news.NewsBundle;
import jospi.models.news.NewsPost;

public final class News {
    private static final String BASE = "/news/";
	
	private final OsuApiClient client;

	protected News(OsuApiClient client) {
		this.client = client;
	}

    public AsyncLazyEnumerable<String, NewsPost[]> getNewsPosts() {
        ExitToken<String> token = new ExitToken<>("");
		Function<ExitToken<String>, CompletableFuture<NewsPost[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				NewsBundle bundle = client.getJson(BASE, map -> {
					map.put("cursor_string", token.getToken());
				});
				token.setNext(bundle.getCursorString());
				return bundle.getNewsPosts();
			});
		return new AsyncLazyEnumerable<>(func, token);
    }

    public CompletableFuture<NewsPost> getNewsPost(String slug) {
        return client.getJsonAsync(BASE+slug);
    }

	public CompletableFuture<NewsPost> getNewsPost(int id) {
		return getNewsPost(Integer.toString(id));
	}
}

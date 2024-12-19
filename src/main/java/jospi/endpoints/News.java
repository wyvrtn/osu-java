package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.models.news.NewsBundle;
import jospi.models.news.NewsPost;
import jospi.util.iterator.AsyncLazyEnumerable;
import jospi.util.iterator.ExitToken;

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
                }, NewsBundle.class);
                token.setNext(bundle.getCursorString());
                return bundle.getNewsPosts();
            });
        return new AsyncLazyEnumerable<>(func, token);
    }

    public CompletableFuture<NewsPost> getNewsPost(String slug) {
        return client.getJsonAsync(BASE + slug, NewsPost.class);
    }

    public CompletableFuture<NewsPost> getNewsPost(int id) {
        return getNewsPost(Integer.toString(id));
    }
}

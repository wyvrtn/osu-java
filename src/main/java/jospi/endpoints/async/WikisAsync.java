package jospi.endpoints.async;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.InternalOsuApiClient;
import jospi.models.wikis.WikiPage;

public final class WikisAsync {

    private final InternalOsuApiClient client;

    protected WikisAsync(InternalOsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<WikiPage> getWikiPage(String locale, String path) {
        return client.getJsonAsync("/wiki/" + locale + "/" + path, WikiPage.class);
    }
}

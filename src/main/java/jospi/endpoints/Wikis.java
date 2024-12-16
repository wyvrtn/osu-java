package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.models.wikis.WikiPage;

public final class Wikis {

    private final OsuApiClient client;

    protected Wikis(OsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<WikiPage> getWikiPage(String locale, String path) {
        return client.getJsonAsync("/wiki/" + locale + "/" + path, WikiPage.class);
    }
}

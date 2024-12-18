package jospi.endpoints.async;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.InternalOsuApiClient;
import jospi.models.changelogs.Build;
import jospi.models.changelogs.ChangelogListing;

public final class ChangelogsAsync {
    private static final String BASE = "/changelog/";

    private final InternalOsuApiClient client;

    protected ChangelogsAsync(InternalOsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<Build> getBuild(String stream, String build) {
        return client.getJsonAsync(BASE + stream + "/" + build, Build.class);
    }

    public CompletableFuture<ChangelogListing> getChangelogListing(String stream, String fromBuild, String toBuild, int maxBuildId) {
        return client.getJsonAsync("/changelog", map -> {
                    map.put("stream", stream);
                    map.put("from", fromBuild);
                    map.put("to", toBuild);
                    map.put("max_id", maxBuildId);
                }, ChangelogListing.class);
    }

    public CompletableFuture<Build> lookupBuildId(int buildId) {
        return client.getJsonAsync(BASE + buildId + "?key=id", Build.class);
    }

    public CompletableFuture<Build> lookupLatestBuild(String stream) {
        return client.getJsonAsync(BASE + stream, Build.class);
    }
}

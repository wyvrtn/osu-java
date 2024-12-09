package jospi.endpoints;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.OsuApiClient;
import jospi.models.changelogs.Build;
import jospi.models.changelogs.ChangelogListing;

public final class Changelogs {
    private static final String BASE = "/changelog/";

    private final OsuApiClient client;

    protected Changelogs(OsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<Build> getBuild(String stream, String build) {
        return client.getJsonAsync(BASE+stream+"/"+build, Build.class);
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
        return client.getJsonAsync(BASE+buildId+"?key=id", Build.class);
    }

    public CompletableFuture<Build> lookupLatestBuild(String stream) {
        return client.getJsonAsync(BASE+stream, Build.class);
    }
}

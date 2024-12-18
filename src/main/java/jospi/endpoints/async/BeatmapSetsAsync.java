package jospi.endpoints.async;

import java.util.concurrent.CompletableFuture;

import jospi.client.core.InternalOsuApiClient;
import jospi.models.beatmaps.BeatmapSetExtended;

public final class BeatmapSetsAsync {
    private static final String BASE = "/beatmapsets/";

    private final InternalOsuApiClient client;

    protected BeatmapSetsAsync(InternalOsuApiClient client) {
        this.client = client;
    }

    public CompletableFuture<BeatmapSetExtended> lookupBeatmapSet(int beatmapId) {
        return client.getJsonAsync(BASE + "lookup?beatmap_id=" + beatmapId, BeatmapSetExtended.class);
    }

    public CompletableFuture<BeatmapSetExtended> getBeatmapSet(int beatmapSetId) {
        return client.getJsonAsync(BASE + beatmapSetId, BeatmapSetExtended.class);
    }
}

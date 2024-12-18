package jospi.endpoints.async;

import jospi.client.core.InternalOsuApiClient;

// API is a work-in-progress, wrapper will be implemented soon

@SuppressWarnings("unused")
public final class BeatmapSetDiscussionsAsync {
    private static final String BASE = "/beatmapsets/discussions";

    private final InternalOsuApiClient client;

    public BeatmapSetDiscussionsAsync(InternalOsuApiClient client) {
        this.client = client;
    }


}

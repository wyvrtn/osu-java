package jospi.endpoints;

import jospi.client.core.OsuApiClient;

// API is a work-in-progress, wrapper will be implemented soon

@SuppressWarnings("unused")
public final class BeatmapSetDiscussions {
	private static final String BASE = "/beatmapsets/discussions";
	
	private OsuApiClient client; 

	public BeatmapSetDiscussions(OsuApiClient client) {
		this.client = client;
	}
	
	
}

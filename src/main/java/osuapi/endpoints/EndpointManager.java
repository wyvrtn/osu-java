package osuapi.endpoints;

import lombok.experimental.Delegate;
import osuapi.client.OsuApiClient;

public final class EndpointManager {

	private OsuApiClient client;
	
	@Delegate
	private BeatmapPacks beatmapPacksDelegate = new BeatmapPacks(client);
	
	@Delegate
	private Beatmaps beatmapsDelegate = new Beatmaps(client);
	
	@Delegate
	private BeatmapSets beatmapSetsDelegate = new BeatmapSets(client);
	
	@Delegate
	private Changelogs changelogsDelegate = new Changelogs(client);
	
	@Delegate
	private Rankings rankingsDelegate = new Rankings(client);
	
	@Delegate
	private Wikis wikisDelegate = new Wikis(client);
	
	private EndpointManager(OsuApiClient client) {
		this.client = client;
	}
	
	public static EndpointManager createInstance(OsuApiClient client) {
		return new EndpointManager(client);
	}
	
}

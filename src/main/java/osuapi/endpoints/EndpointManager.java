package osuapi.endpoints;

import lombok.experimental.Delegate;
import osuapi.client.OsuApiClient;

public final class EndpointManager {

	private OsuApiClient client;
	
	@Delegate
	private BeatmapPacks beatmapPacksDelegate;
	
	@Delegate
	private Beatmaps beatmapsDelegate;
	
	@Delegate
	private BeatmapSets beatmapSetsDelegate;
	
	@Delegate
	private Changelogs changelogsDelegate;
	
	@Delegate
	private Rankings rankingsDelegate;
	
	@Delegate
	private Wikis wikisDelegate;
	
	private EndpointManager(OsuApiClient client) {
		this.client = client;
		beatmapPacksDelegate = new BeatmapPacks(this.client);
		beatmapsDelegate = new Beatmaps(this.client);
		beatmapSetsDelegate = new BeatmapSets(this.client);
		changelogsDelegate = new Changelogs(this.client);
		rankingsDelegate = new Rankings(this.client);
		wikisDelegate = new Wikis(this.client);
	}
	
	public static EndpointManager createInstance(OsuApiClient client) {
		return new EndpointManager(client);
	}

	@Override
	public String toString() {
		return "EndpointManager [client=" + client + "]";
	}
}

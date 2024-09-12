package osuapi.endpoints;

import lombok.experimental.Delegate;

public final class EndpointManager {

	private OsuApiClient client;
	
	@Delegate
	private BeatmapPacks beatmapPacksDelegate = new BeatmapPacks();
	
	@Delegate
	private Beatmaps beatmapsDelegate = new Beatmaps();
	
	@Delegate
	private BeatmapSets beatmapSetsDelegate = new BeatmapSets();
	
	@Delegate
	private Changelogs changelogsDelegate = new Changelogs();
	
	@Delegate
	private Rankings rankingsDelegate = new Rankings();
	
	@Delegate
	private Wikis wikisDelegate = new Wikis();
	
	private EndpointManager() {}
	
	public static EndpointManager createInstance() {
		return new EndpointManager();
	}
	
}

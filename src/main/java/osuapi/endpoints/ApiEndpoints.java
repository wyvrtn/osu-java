package osuapi.endpoints;

import lombok.experimental.Delegate;
import osuapi.client.core.OsuApiClient;

public final class ApiEndpoints {

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
	private Comments commentsDelegate;

	@Delegate
	private Events eventsDelegate;

	@Delegate
	private News newsDelegate;
	
	@Delegate
	private Rankings rankingsDelegate;

	@Delegate
	private Users usersDelegate;
	
	@Delegate
	private Wikis wikisDelegate;
	
	private ApiEndpoints(OsuApiClient client) {
		this.client = client;
		beatmapPacksDelegate = new BeatmapPacks(this.client);
		beatmapsDelegate = new Beatmaps(this.client);
		beatmapSetsDelegate = new BeatmapSets(this.client);
		changelogsDelegate = new Changelogs(this.client);
		commentsDelegate = new Comments(this.client);
		eventsDelegate = new Events(this.client);
		newsDelegate = new News(this.client);
		rankingsDelegate = new Rankings(this.client);
		usersDelegate = new Users(this.client);
		wikisDelegate = new Wikis(this.client);
	}
	
	public static ApiEndpoints createInstance(OsuApiClient client) {
		return new ApiEndpoints(client);
	}

	@Override
	public String toString() {
		return "EndpointManager [client=" + client + "]";
	}
}

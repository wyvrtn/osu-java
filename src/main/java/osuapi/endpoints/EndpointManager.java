package osuapi.endpoints;

import java.util.Arrays;

import lombok.experimental.Delegate;
import osuapi.client.core.OsuApiClient;

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
	private Comments commentsDelegate;

	@Delegate
	private News newsDelegate;
	
	@Delegate
	private Rankings rankingsDelegate;

	@Delegate
	private Users usersDelegate;
	
	@Delegate
	private Wikis wikisDelegate;
	
	private EndpointManager(OsuApiClient client) {
		this.client = client;
		Arrays.asList(this.getClass().getDeclaredFields()).stream()
		.filter(field -> !(field.getAnnotation(Delegate.class)==null))
		.forEach(field -> {
			try {
				field.set(this, field.getType().getDeclaredConstructor(OsuApiClient.class).newInstance(this.client));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		beatmapPacksDelegate = new BeatmapPacks(this.client);
		beatmapsDelegate = new Beatmaps(this.client);
		beatmapSetsDelegate = new BeatmapSets(this.client);
		changelogsDelegate = new Changelogs(this.client);
		commentsDelegate = new Comments(this.client);
		newsDelegate = new News(this.client);
		rankingsDelegate = new Rankings(this.client);
		usersDelegate = new Users(this.client);
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

package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import osuapi.client.core.OsuApiClient;
import osuapi.models.users.User;

public final class Rankings {
	
	private OsuApiClient client;

	protected Rankings(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<User[]> getKudosuRanking(int page) {
		return CompletableFuture.supplyAsync(() -> 
			client.getJson("/rankings/kudosu?page"+page)
		);
	}
}

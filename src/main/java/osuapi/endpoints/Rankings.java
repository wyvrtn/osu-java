package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import osuapi.client.OsuApiClient;
import osuapi.client.resources.OsuApiException;
import osuapi.models.users.User;

public final class Rankings {
	
	private OsuApiClient client;

	protected Rankings(OsuApiClient client) {
		this.client = client;
	}
	
	public CompletableFuture<User[]> getKudosuRanking(int page) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/rankings/kudosu?page"+page, new User[50]);
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}

package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import osuapi.client.OsuApiClient;
import osuapi.framework.exception.OsuApiException;
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

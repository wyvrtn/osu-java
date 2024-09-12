package osuapi.endpoints;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import osuapi.client.OsuApiClient;
import osuapi.framework.exception.OsuApiException;
import osuapi.models.users.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Rankings {
	
	@Autowired
	private OsuApiClient client;
	
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

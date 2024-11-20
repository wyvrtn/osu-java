package jospi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.enums.matches.MatchBundleSort;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.matches.Match;
import jospi.models.matches.MatchBundle;
import jospi.models.matches.MatchesBundle;

public class Matches {
    private static final String BASE = "/matches/";

    private OsuApiClient client;

	protected Matches(OsuApiClient client) {
		this.client = client;
	}

	public AsyncLazyEnumerable<String, Match[]> getMatches(int limit, MatchBundleSort sort) {
		ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<Match[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				Map<String, Object> params = new HashMap<>();
				params.put("limit", limit);
				params.put("sort", sort);
				MatchesBundle packs = client.getJson(BASE, params);
				token.setNext(packs.getCursorString());
				return packs.getMatches();
			});
		return new AsyncLazyEnumerable<>(func, token);
	}

	public CompletableFuture<MatchBundle> getMatch(int matchId, int before, int after, int limit) {
		return CompletableFuture.supplyAsync(() ->{
			Map<String, Object> params = new HashMap<>();
			params.put("before", before);
			params.put("after", after);
			params.put("limit", limit);
			return client.getJson(BASE+matchId, params);
		});
	}
}

package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.enums.matches.MatchBundleSort;
import jospi.models.matches.Match;
import jospi.models.matches.MatchBundle;
import jospi.models.matches.MatchesBundle;
import jospi.util.iterator.AsyncLazyEnumerable;
import jospi.util.iterator.ExitToken;

public final class Matches {
    private static final String BASE = "/matches/";

    private final OsuApiClient client;

    protected Matches(OsuApiClient client) {
        this.client = client;
    }

    public AsyncLazyEnumerable<String, Match[]> getMatches(int limit, MatchBundleSort sort) {
        ExitToken<String> token = new ExitToken<>("");
        Function<ExitToken<String>, CompletableFuture<Match[]>> func = t ->
            CompletableFuture.supplyAsync(() -> {
                MatchesBundle packs = client.getJson(BASE, map -> {
                    map.put("limit", limit);
                    map.put("sort", sort);
                }, MatchesBundle.class);
                token.setNext(packs.getCursorString());
                return packs.getMatches();
            });
        return new AsyncLazyEnumerable<>(func, token);
    }

    public CompletableFuture<MatchBundle> getMatch(int matchId, int before, int after, int limit) {
        return client.getJsonAsync(BASE + matchId, map -> {
                map.put("before", before);
                map.put("after", after);
                map.put("limit", limit);
            }, MatchBundle.class);
    }
}

package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.fasterxml.jackson.core.type.TypeReference;

import jospi.client.core.OsuApiClient;
import jospi.enums.multiplayer.MultiplayerRoomMode;
import jospi.enums.multiplayer.MultiplayerRoomSort;
import jospi.enums.multiplayer.MultiplayerRoomTypeGroup;
import jospi.enums.multiplayer.MultiplayerScoresSort;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.multiplayer.MultiplayerScores;

public final class Multiplayer {
    private static final String BASE = "/rooms/";

    private final OsuApiClient client;

    protected Multiplayer(OsuApiClient client) {
        this.client = client;
    }

    public AsyncLazyEnumerable<String, MultiplayerScores> getMultiplayerScores(int roomId, int playlistId, int limit, MultiplayerScoresSort sort) {
        ExitToken<String> token = new ExitToken<>("");
        Function<ExitToken<String>, CompletableFuture<MultiplayerScores>> func = tkn ->
            CompletableFuture.supplyAsync(() -> {
                MultiplayerScores multiplayerScores = client.getJson(BASE + roomId + "/playlist/" + playlistId + "/scores", map -> {
                    map.put("limit", limit);
                    map.put("sort", sort);
                    map.put("cursor_string", tkn.getToken());
                }, MultiplayerScores.class);
                tkn.setNext(multiplayerScores.getCursorString());
                return multiplayerScores;
            });
        return new AsyncLazyEnumerable<>(func, token);
    }

    public <T> CompletableFuture<T> getRooms(int limit, MultiplayerRoomMode mode, String seasonId, MultiplayerRoomSort sort, MultiplayerRoomTypeGroup typeGroup) {
        client.requiresUser();
        return client.getJsonAsync(BASE, map -> {
                map.put("limit", limit);
                map.put("mode", mode);
                map.put("season_id", seasonId);
                map.put("sort", sort);
                map.put("type_group", typeGroup);
            }, new TypeReference<T>() { });
    }
}

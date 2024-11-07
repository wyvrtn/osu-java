package osuapi.endpoints;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.core.OsuApiClient;
import osuapi.client.resources.ClientUtil;
import osuapi.enums.multiplayer.MultiplayerRoomMode;
import osuapi.enums.multiplayer.MultiplayerRoomSort;
import osuapi.enums.multiplayer.MultiplayerRoomTypeGroup;
import osuapi.enums.multiplayer.MultiplayerScoresSort;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.multiplayer.MultiplayerScores;

public class Multiplayer {
	private static final String BASE = "/rooms/";
	
	private OsuApiClient client;

	protected Multiplayer(OsuApiClient client) {
		this.client = client;
	}

	public AsyncLazyEnumerable<String, MultiplayerScores> getMultiplayerScores(int roomId, int playlistId, int limit, MultiplayerScoresSort sort) {
		ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<MultiplayerScores>> func = tkn -> 
			CompletableFuture.supplyAsync(() -> {
				MultiplayerScores multiplayerScores = client.getJson(BASE+roomId+"/playlist/"+playlistId+"/scores",
				ClientUtil.buildQueryMap(Arrays.asList("limit", "sort", "cursor_string"), limit, sort, tkn.getToken()));
				tkn.setNext(multiplayerScores.getCursorString());
				return multiplayerScores;
			});
		return new AsyncLazyEnumerable<>(func, token);
	}

    public <T> CompletableFuture<T> getRooms(int limit, MultiplayerRoomMode mode, String seasonId, MultiplayerRoomSort sort, MultiplayerRoomTypeGroup typeGroup) {
		client.requiresUser();
        return client.getJsonAsync(BASE, ClientUtil.buildQueryMap(Arrays.asList("limit", "mode", "season_id", "sort", "type_group"), limit, mode, seasonId, sort, typeGroup));
    }
}

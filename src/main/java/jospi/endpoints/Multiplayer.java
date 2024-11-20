package jospi.endpoints;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.client.resources.ClientUtil;
import jospi.enums.multiplayer.MultiplayerRoomMode;
import jospi.enums.multiplayer.MultiplayerRoomSort;
import jospi.enums.multiplayer.MultiplayerRoomTypeGroup;
import jospi.enums.multiplayer.MultiplayerScoresSort;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.multiplayer.MultiplayerScores;

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

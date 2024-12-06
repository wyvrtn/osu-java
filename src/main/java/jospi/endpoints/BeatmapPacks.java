package jospi.endpoints;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.enums.beatmaps.BeatmapPackType;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.beatmaps.BeatmapPack;
import jospi.models.beatmaps.BeatmapPackExtended;

public final class BeatmapPacks {
	private static final String BASE = "/beatmaps/";

	private final OsuApiClient client;

	protected BeatmapPacks(OsuApiClient client) {
		this.client = client;
	}

	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks() {
		return getBeatmapPacks(BeatmapPackType.STANDARD);
	}

	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks(BeatmapPackType type) {
		ExitToken<String> token = new ExitToken<>("");
		Function<ExitToken<String>, CompletableFuture<BeatmapPack[]>> func = t ->
			CompletableFuture.supplyAsync(() -> {
				BeatmapPackExtended packs = client.getJson(BASE + "packs", map -> {
					map.put("type", type.toString());
					map.put("cursor_string", token.getToken());
				});
				token.setNext(packs.getCursorString());
				return packs.getBeatmapPacks();
			});
		return new AsyncLazyEnumerable<>(func, token);
	}

	public CompletableFuture<BeatmapPack> getBeatmapPack(String tag) {
		return getBeatmapPack(tag, false);
	}

	public CompletableFuture<BeatmapPack> getBeatmapPack(String tag, boolean legacyOnly) {
		return CompletableFuture.supplyAsync(() ->
			client.getJson(BASE + "packs/"+ tag + "?" + (legacyOnly? 1:0))
		);
	}
}

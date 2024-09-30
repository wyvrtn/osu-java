package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.OsuApiClient;
import osuapi.enums.BeatmapPackType;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.beatmaps.BeatmapPack;
import osuapi.models.beatmaps.BeatmapPackExtended;

// API docs: https://osu.ppy.sh/docs/index.html#beatmap-packs
public final class BeatmapPacks {
	private static final String BASE = "/beatmaps/";
	
	private OsuApiClient client;

	protected BeatmapPacks(OsuApiClient client) {
		this.client = client;
	}

	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks() {
		return getBeatmapPacks(BeatmapPackType.STANDARD);
	}
	
	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks(BeatmapPackType type) {
		ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<BeatmapPack[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				BeatmapPackExtended packs = new BeatmapPackExtended();
				Map<String, Object> params = new HashMap<>();
				params.put("type", type.toString());
				params.put("cursor_string", token.getToken());
				packs = client.getJson(BASE + "packs", params);
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

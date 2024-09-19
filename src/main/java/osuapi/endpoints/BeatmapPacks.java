package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.OsuApiClient;
import osuapi.client.resources.ClientUtil;
import osuapi.enums.BeatmapPackType;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.beatmaps.BeatmapPack;
import osuapi.models.beatmaps.BeatmapPackExtended;

// API docs: https://osu.ppy.sh/docs/index.html#beatmap-packs
public final class BeatmapPacks {
	
	private OsuApiClient client;

	protected BeatmapPacks(OsuApiClient client) {
		this.client = client;
	}

	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks() {
		return getBeatmapPacks(null);
	}
	
	public AsyncLazyEnumerable<String, BeatmapPack[]> getBeatmapPacks(final BeatmapPackType type) {
		String url = "/beatmaps/packs";
		ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<BeatmapPack[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				BeatmapPackExtended packs = new BeatmapPackExtended();
				Map<String, Object> params = new HashMap<>();
				params.put("type", ClientUtil.nullishCoalesce(type, BeatmapPackType.STANDARD).toString());
				params.put("cursor_string", token.getToken());
				packs = client.getJson(url 
						+ client.buildQueryString(params), new BeatmapPackExtended());
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
			client.getJson("/beatmaps/packs/"+ tag + "?" + (legacyOnly? 1:0), new BeatmapPack())
		);
	}
}

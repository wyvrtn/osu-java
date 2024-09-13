package osuapi.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.OsuApiClient;
import osuapi.enums.BeatmapPackType;
import osuapi.framework.exception.OsuApiException;
import osuapi.framework.iterator.AsyncLazyEnumerator;
import osuapi.framework.iterator.ExitToken;
import osuapi.models.beatmaps.BeatmapPack;
import osuapi.models.beatmaps.BeatmapPackExtended;

// API docs: https://osu.ppy.sh/docs/index.html#beatmap-packs
public final class BeatmapPacks {
	
	private OsuApiClient client;

	protected BeatmapPacks(OsuApiClient client) {
		this.client = client;
	}
	
	public AsyncLazyEnumerator<String, BeatmapPack[]> getBeatmapPacks(final BeatmapPackType type) {
		String url = "/beatmaps/packs";
		ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
		Function<ExitToken<String>, CompletableFuture<BeatmapPack[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				BeatmapPackExtended packs = new BeatmapPackExtended();
				Map<String, Object> params = new HashMap<>();
				params.put("type", type==null? "Standard" : type.toString());
				params.put("cursor_string", token.getToken());
				try {
					packs = client.getJson(url 
							+ client.buildQueryString(params), new BeatmapPackExtended());
				} catch (UnsupportedEncodingException | OsuApiException e) {
					e.printStackTrace();
				}
				token.setNext(packs.getCursorString());
				return packs.getBeatmapPacks();
			});
		return new AsyncLazyEnumerator<>(func, token);
	}
	
	public CompletableFuture<BeatmapPack> getBeatmapPack(String tag) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return client.getJson("/beatmaps/packs/"+tag, new BeatmapPack());
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}

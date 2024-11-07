package osuapi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import osuapi.client.core.OsuApiClient;
import osuapi.enums.events.PostSort;
import osuapi.iterator.AsyncLazyEnumerable;
import osuapi.iterator.ExitToken;
import osuapi.models.events.Event;
import osuapi.models.generic.CursorResponse;

public class Events {
    private static final String BASE = "/events/";

    private OsuApiClient client;

	protected Events(OsuApiClient client) {
		this.client = client;
	}

    public AsyncLazyEnumerable<String, Event[]> getEvents() {
        return getEvents(PostSort.IDDESCENDING);
    }

    public AsyncLazyEnumerable<String, Event[]> getEvents(PostSort sort) {
        ExitToken<String> token = new ExitToken<>("", Objects::nonNull);
    	Function<ExitToken<String>, CompletableFuture<Event[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				Map<String, Object> params = new HashMap<>();
				params.put("sort", sort);
				params.put("cursor_string", token.getToken());
				CursorResponse<Event> events = client.getJson(BASE, params);
				token.setNext(events.getCursorString());
				return events.getData();
			});
		return new AsyncLazyEnumerable<>(func, token);
    }
}

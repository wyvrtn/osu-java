package jospi.endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import jospi.client.core.OsuApiClient;
import jospi.enums.events.PostSort;
import jospi.iterator.AsyncLazyEnumerable;
import jospi.iterator.ExitToken;
import jospi.models.events.Event;
import jospi.models.generic.CursorResponse;

public class Events {
    private static final String BASE = "/events/";

    private final OsuApiClient client;

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

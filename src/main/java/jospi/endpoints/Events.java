package jospi.endpoints;

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
        ExitToken<String> token = new ExitToken<>("");
    	Function<ExitToken<String>, CompletableFuture<Event[]>> func = t -> 
			CompletableFuture.supplyAsync(() -> {
				CursorResponse<Event> events = client.getJson(BASE, map -> {
					map.put("sort", sort);
					map.put("cursor_string", token.getToken());
				});
				token.setNext(events.getCursorString());
				return events.getData();
			});
		return new AsyncLazyEnumerable<>(func, token);
    }
}

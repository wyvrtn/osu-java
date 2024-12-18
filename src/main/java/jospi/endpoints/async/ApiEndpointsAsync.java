package jospi.endpoints.async;

import jospi.client.core.InternalOsuApiClient;
import jospi.endpoints.AbstractApiEndpoints;
import lombok.experimental.Delegate;

public final class ApiEndpointsAsync extends AbstractApiEndpoints {

    @Delegate
    private final BeatmapPacksAsync beatmapPacksDelegate;

    @Delegate
    private final BeatmapsAsync beatmapsDelegate;

    @Delegate
    private final BeatmapSetsAsync beatmapSetsDelegate;

    @Delegate
    private final ChangelogsAsync changelogsDelegate;

    @Delegate
    private final CommentsAsync commentsDelegate;

    @Delegate
    private final EventsAsync eventsDelegate;

    @Delegate
    private final HomeAsync homeDelegate;

    @Delegate
    private final MatchesAsync matchesDelegate;

    @Delegate
    private final MultiplayerAsync multiplayerDelegate;

    @Delegate
    private final NewsAsync newsDelegate;

    @Delegate
    private final RankingsAsync rankingsDelegate;

    @Delegate
    private final UsersAsync usersDelegate;

    @Delegate
    private final WikisAsync wikisDelegate;

    private ApiEndpointsAsync(InternalOsuApiClient client) {
        super(client);
        beatmapPacksDelegate = new BeatmapPacksAsync(client);
        beatmapsDelegate = new BeatmapsAsync(client);
        beatmapSetsDelegate = new BeatmapSetsAsync(client);
        changelogsDelegate = new ChangelogsAsync(client);
        commentsDelegate = new CommentsAsync(client);
        eventsDelegate = new EventsAsync(client);
        homeDelegate = new HomeAsync(client);
        matchesDelegate = new MatchesAsync(client);
        multiplayerDelegate = new MultiplayerAsync(client);
        newsDelegate = new NewsAsync(client);
        rankingsDelegate = new RankingsAsync(client);
        usersDelegate = new UsersAsync(client);
        wikisDelegate = new WikisAsync(client);
    }

    public static ApiEndpointsAsync createInstance(InternalOsuApiClient client) {
        return new ApiEndpointsAsync(client);
    }
}

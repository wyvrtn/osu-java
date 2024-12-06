package jospi.endpoints;

import jospi.client.core.OsuApiClient;
import lombok.experimental.Delegate;

public final class ApiEndpoints {

    private final OsuApiClient client;

    @Delegate
    private final BeatmapPacks beatmapPacksDelegate;

    @Delegate
    private final Beatmaps beatmapsDelegate;

    @Delegate
    private final BeatmapSets beatmapSetsDelegate;

    @Delegate
    private final Changelogs changelogsDelegate;

    @Delegate
    private final Comments commentsDelegate;

    @Delegate
    private final Events eventsDelegate;

    @Delegate
    private final Home homeDelegate;

    @Delegate
    private final Matches matchesDelegate;

    @Delegate
    private final Multiplayer multiplayerDelegate;

    @Delegate
    private final News newsDelegate;

    @Delegate
    private final Rankings rankingsDelegate;

    @Delegate
    private final Users usersDelegate;

    @Delegate
    private final Wikis wikisDelegate;

    private ApiEndpoints(OsuApiClient client) {
        this.client = client;
        beatmapPacksDelegate = new BeatmapPacks(this.client);
        beatmapsDelegate = new Beatmaps(this.client);
        beatmapSetsDelegate = new BeatmapSets(this.client);
        changelogsDelegate = new Changelogs(this.client);
        commentsDelegate = new Comments(this.client);
        eventsDelegate = new Events(this.client);
        homeDelegate = new Home(this.client);
        matchesDelegate = new Matches(this.client);
        multiplayerDelegate = new Multiplayer(this.client);
        newsDelegate = new News(this.client);
        rankingsDelegate = new Rankings(this.client);
        usersDelegate = new Users(this.client);
        wikisDelegate = new Wikis(this.client);
    }

    public static ApiEndpoints createInstance(OsuApiClient client) {
        return new ApiEndpoints(client);
    }

    @Override
    public String toString() {
        return "EndpointManager [client=" + client + "]";
    }
}

package osuapi.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import lombok.Getter;
import lombok.experimental.Delegate;
import osuapi.client.authorization.RequestBundle;
import osuapi.client.core.AbstractApiAuthorization;
import osuapi.client.core.OsuApiClient;
import osuapi.endpoints.ApiEndpoints;

public final class MultiApiClient<K> {
    public final ApiEndpoints endpoints;

    @Delegate(excludes=OsuApiClientDelgationExclusions.class)
    private final OsuApiClient client;

    @Getter
    private final ConcurrentMap<K, AbstractApiAuthorization> authorizationInstances;

    public MultiApiClient(AbstractApiAuthorization auth) {
        this(auth, new RequestBundle());
    }

    public MultiApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
        client = new OsuApiClient(auth, bundle);
        endpoints = client.endpoints;
        authorizationInstances = new ConcurrentHashMap<>();
    }

    public void switchInstance(K key) {
        client.updateAuthorization(authorizationInstances.get(key));
    }

    private interface OsuApiClientDelgationExclusions {
        public void updateAuthorization(AbstractApiAuthorization newAuth);
    }
}

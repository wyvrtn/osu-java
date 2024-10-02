package osuapi.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.experimental.Delegate;
import osuapi.client.authorization.RequestBundle;
import osuapi.endpoints.EndpointManager;

public final class MultiApiClient {
    public final EndpointManager endpoints;

    @Delegate
    private final OsuApiClient client;

    @Getter
    private final List<AbstractApiAuthorization> authorizationInstances;

    public MultiApiClient(AbstractApiAuthorization auth) {
        this(auth, new RequestBundle());
    }

    public MultiApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
        client = new OsuApiClient(auth, bundle);
        endpoints = client.endpoints;
        authorizationInstances = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void switchInstance(int key) {
        client.updateAuthorization(authorizationInstances.get(key));
    }
}

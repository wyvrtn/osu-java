package jospi.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jospi.client.authorization.ClientCredentialsGrant;
import jospi.client.core.AbstractApiAuthorization;
import jospi.client.core.OsuApiClient;
import jospi.client.request.RequestBundle;
import jospi.endpoints.ApiEndpoints;
import lombok.Getter;
import lombok.experimental.Delegate;

public final class MultiApiClient<K> {
    public final ApiEndpoints endpoints;

    @Delegate(excludes=OsuApiClientDelgationExclusions.class)
    private final OsuApiClient client;

    @Getter
    private final ConcurrentMap<K, AbstractApiAuthorization> authorizationInstances;

    public MultiApiClient(int clientId, String clientSecret) {
        this(new ClientCredentialsGrant(clientId, clientSecret));
    }

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

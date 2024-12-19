package jospi.client.core;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.type.TypeReference;

import jospi.client.authorization.ClientCredentialsGrant;
import jospi.client.components.Dictionary;
import jospi.client.request.HttpMethod;
import jospi.client.request.NetIOUtilities;
import jospi.client.request.HttpClientCreator;
import jospi.endpoints.ApiEndpoints;
/**
* The core class of this library.
* <p>
* Contains wrapper methods for accessing endpoints asynchronously
* </p>
*/
public final class OsuApiClient implements NetIOUtilities, Serializable {
    private static final long serialVersionUID = 727L;

    private final transient ApiEndpoints apiEndpoints;
    private AbstractApiAuthorizationContainer authorization;
    private final transient OsuApiClientInternalBlockingStatefulHttpServiceProvider svc;

    public OsuApiClient(final int clientId, final String clientSecret) {
        this(new ClientCredentialsGrant(clientId, clientSecret));
    }

    public OsuApiClient(final AbstractApiAuthorization auth) {
        this(auth, new HttpClientCreator());

    }

    public OsuApiClient(final AbstractApiAuthorization auth, final HttpClientCreator bundle) {
        apiEndpoints = ApiEndpoints.createInstance(this);
        authorization = AbstractApiAuthorizationContainer.newInstance(auth);
        svc = new OsuApiClientInternalBlockingStatefulHttpServiceProvider(bundle, authorization);
    }

    public ApiEndpoints endpoints() {
        return apiEndpoints;
    }

    public void updateAuthorization(final AbstractApiAuthorization newAuth) {
        synchronized (this) {
            authorization.setInstance(newAuth);
            ensureAccessToken();
        }
    }

    public void requiresUser() {
        if (authorization.getInstance() instanceof ClientCredentialsGrant) {
            throw new IllegalStateException("The method called must use Authorization Code Grant");
        }
    }

    public void ensureAccessToken() {
        synchronized (this) {
            if (authorization.getInstance().getExpirationDate().isAfter(OffsetDateTime.now())) {
                return;
            }
            if (!authorization.getInstance().isStatus()) {
                authorization.getInstance().authorizationFlow(svc);
            } else {
                authorization.getInstance().refreshAccessToken(svc);
            }
        }
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Class<T> clazz) {
        return getJsonAsync(url, HttpMethod.GET, clazz);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final HttpMethod method, final Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> getJson(url, method, clazz));
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Dictionary<String, Object> queryParams, final Class<T> clazz) {
        return getJsonAsync(url, queryParams, HttpMethod.GET, clazz);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Map<String, Object> queryParams, final Class<T> clazz) {
        return getJsonAsync(url, queryParams, HttpMethod.GET, clazz);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Dictionary<String, Object> queryParams, final HttpMethod method, final Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, method, clazz));
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Map<String, Object> queryParams, final HttpMethod method, final Class<T> clazz) {
        return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, method, clazz));
    }

    public <T> T getJson(final String url, final Dictionary<String, Object> queryParams, final Class<T> clazz) {
        return getJson(url + toQueryString(queryParams), HttpMethod.GET, clazz);
    }

    public <T> T getJson(final String url, final Map<String, Object> queryParams, final Class<T> clazz) {
        return getJson(url + toQueryString(queryParams), HttpMethod.GET, clazz);
    }

    public <T> T getJson(final String url, final Dictionary<String, Object> queryParams, final HttpMethod method, final Class<T> clazz) {
        return getJson(url + toQueryString(queryParams), method, clazz);
    }

    public <T> T getJson(final String url, final Map<String, Object> queryParams, final HttpMethod method, final Class<T> clazz) {
        return getJson(url + toQueryString(queryParams), method, clazz);
    }

    public <T> T getJson(final String url, final Class<T> clazz) {
        return getJson(url, HttpMethod.GET, clazz);
    }

    public <T> T getJson(final String url, final HttpMethod method, final Class<T> clazz) {
        ensureAccessToken();
        return svc.genericGetJson(url, method, clazz);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final TypeReference<T> tf) {
        return getJsonAsync(url, HttpMethod.GET, tf);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final HttpMethod method, final TypeReference<T> tf) {
        return CompletableFuture.supplyAsync(() -> getJson(url, method, tf));
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Dictionary<String, Object> queryParams, final TypeReference<T> tf) {
        return getJsonAsync(url, queryParams, HttpMethod.GET, tf);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Map<String, Object> queryParams, final TypeReference<T> tf) {
        return getJsonAsync(url, queryParams, HttpMethod.GET, tf);
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Dictionary<String, Object> queryParams, final HttpMethod method, final TypeReference<T> tf) {
        return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, method, tf));
    }

    public <T> CompletableFuture<T> getJsonAsync(final String url, final Map<String, Object> queryParams, final HttpMethod method, final TypeReference<T> tf) {
        return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, method, tf));
    }

    public <T> T getJson(final String url, final Dictionary<String, Object> queryParams, final TypeReference<T> tf) {
        return getJson(url + toQueryString(queryParams), HttpMethod.GET, tf);
    }

    public <T> T getJson(final String url, final Map<String, Object> queryParams, final TypeReference<T> tf) {
        return getJson(url + toQueryString(queryParams), HttpMethod.GET, tf);
    }

    public <T> T getJson(final String url, final Dictionary<String, Object> queryParams, final HttpMethod method, final TypeReference<T> tf) {
        return getJson(url + toQueryString(queryParams), method, tf);
    }

    public <T> T getJson(final String url, final Map<String, Object> queryParams, final HttpMethod method, final TypeReference<T> tf) {
        return getJson(url + toQueryString(queryParams), method, tf);
    }

    public <T> T getJson(final String url, final TypeReference<T> tf) {
        return getJson(url, HttpMethod.GET, tf);
    }

    public <T> T getJson(final String url, final HttpMethod method, final TypeReference<T> tf) {
        ensureAccessToken();
        return svc.genericGetJson(url, method, tf);
    }
}

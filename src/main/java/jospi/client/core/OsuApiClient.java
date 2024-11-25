package jospi.client.core;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import jospi.client.authorization.ClientCredentialsGrant;
import jospi.client.request.HttpMethod;
import jospi.client.request.NetIOUtilities;
import jospi.client.request.RequestBundle;
import jospi.endpoints.ApiEndpoints;

public final class OsuApiClient implements NetIOUtilities {
	public final ApiEndpoints endpoints;
	private AbstractApiAuthorizationContainer authorization; 
	protected final OsuApiClientInternalBlockingStatefulHttpServiceProvider svc;
	
    public OsuApiClient(int clientId, String clientSecret) {
    	this(new ClientCredentialsGrant(clientId, clientSecret));
    }
	
	public OsuApiClient(AbstractApiAuthorization auth) {
		this(auth, new RequestBundle());
		
	}
	
	public OsuApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
		endpoints = ApiEndpoints.createInstance(this);
		authorization = AbstractApiAuthorizationContainer.newInstance(auth);
		svc = new OsuApiClientInternalBlockingStatefulHttpServiceProvider(bundle, authorization);
	}

	public void updateAuthorization(AbstractApiAuthorization newAuth) {
		synchronized(this) {
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
		synchronized(this) {
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

	public <T> CompletableFuture<T> getJsonAsync(String url) {
		return getJsonAsync(url, HttpMethod.GET);
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, HttpMethod method) {
		return CompletableFuture.supplyAsync(() -> getJson(url, method));
	}

	public <T> CompletableFuture<T> getJsonAsync(String url, Map<String, Object> queryParams) {
		return getJsonAsync(url, queryParams, HttpMethod.GET);
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, Map<String, Object> queryParams, HttpMethod method) {
		return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, method));
	}

	public <T> T getJson(String url, Map<String, Object> queryParams) {
		return getJson(url + toQueryString(queryParams), HttpMethod.GET);
	}
	
	public <T> T getJson(String url, Map<String, Object> queryParams, HttpMethod method) {
		return getJson(url + toQueryString(queryParams), method);
	}

	public <T> T getJson(String url) {
		return getJson(url, HttpMethod.GET);
	}
	
	public <T> T getJson(String url, HttpMethod method) {
		ensureAccessToken();
		return svc.genericGetJson(url, method);
	}
}
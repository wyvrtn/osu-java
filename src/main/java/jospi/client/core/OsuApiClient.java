package jospi.client.core;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jospi.client.authorization.ClientCredentialsGrant;
import jospi.client.request.RequestBundle;
import jospi.client.resources.ClientUtil;
import jospi.client.resources.OsuApiException;
import jospi.endpoints.ApiEndpoints;

public final class OsuApiClient {
	public final ApiEndpoints endpoints;
	private AbstractApiAuthorizationContainer authorization; 
	protected final OsuApiClientInternal svc;
	
    public OsuApiClient(int clientId, String clientSecret) {
    	this(new ClientCredentialsGrant(clientId, clientSecret));
    }
	
	public OsuApiClient(AbstractApiAuthorization auth) {
		this(auth, new RequestBundle());
		
	}
	
	public OsuApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
		endpoints = ApiEndpoints.createInstance(this);
		authorization = AbstractApiAuthorizationContainer.newInstance(auth);
		svc = new OsuApiClientInternal(bundle, authorization);
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
		return getJson(url + ClientUtil.buildQueryString(queryParams), HttpMethod.GET);
	}
	
	public <T> T getJson(String url, Map<String, Object> queryParams, HttpMethod method) {
		return getJson(url + ClientUtil.buildQueryString(queryParams), method);
	}

	public <T> T getJson(String url) {
		return getJson(url, HttpMethod.GET);
	}
	
	public <T> T getJson(String url, HttpMethod method) {
		ensureAccessToken();
		ResponseEntity<T> entity = svc.genericGetJson(url, method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
		}
		return entity.getBody();
	}
}
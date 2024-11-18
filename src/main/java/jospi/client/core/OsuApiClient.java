package jospi.client.core;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jospi.client.authorization.ClientCredentialsGrant;
import jospi.client.request.RequestBundle;
import jospi.client.resources.ClientUtil;
import jospi.client.resources.OsuApiException;
import jospi.endpoints.ApiEndpoints;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public final ApiEndpoints endpoints;
	private AbstractApiAuthorizationContainer authorization; 
	protected final OsuApiClientInternal svc;
	
	public OsuApiClient(AbstractApiAuthorization auth) {
		this(auth, new RequestBundle());
		
	}
	
	public OsuApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
		endpoints = ApiEndpoints.createInstance(this);
		authorization = AbstractApiAuthorizationContainer.newInstance(auth);
		svc = new OsuApiClientInternal(bundle, authorization);
	}

	public synchronized void updateAuthorization(AbstractApiAuthorization newAuth) {
		authorization.setInstance(newAuth);
		ensureAccessToken();
	}

	public void requiresUser() {
		if (authorization.getInstance() instanceof ClientCredentialsGrant) {
			throw new IllegalStateException("The method called must use Authorization Code Grant");
		}
	}
	
	public synchronized void ensureAccessToken() {
		if (authorization.getInstance().getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		if (!authorization.getInstance().isStatus()) {
			authorization.getInstance().authorizationFlow(svc);
		} else {
			authorization.getInstance().refreshAccessToken(svc);
		}
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, methods));
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, Map<String, Object> queryParams, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, methods));
	}
	
	public <T> T getJson(String url, Map<String, Object> queryParams, HttpMethod... methods) {
		return getJson(url + ClientUtil.buildQueryString(queryParams), methods);
	}
	
	public <T> T getJson(String url, HttpMethod... methods) {
		ensureAccessToken();
		HttpMethod method = ClientUtil.optDefault(methods, HttpMethod.GET);
		ResponseEntity<T> entity = svc.genericGetJson(url, method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
		}
		LOG.info("Request Successful");
		return entity.getBody();
	}
}
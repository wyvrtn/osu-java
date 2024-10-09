package osuapi.client.core;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import osuapi.client.authorization.ClientCredentialsGrant;
import osuapi.client.authorization.RequestBundle;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.endpoints.EndpointManager;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public final EndpointManager endpoints;
	private AbstractApiAuthorization authorization; 
	protected final OsuApiClientInternal svc;
	
	public OsuApiClient(AbstractApiAuthorization auth) {
		this(auth, new RequestBundle());
		
	}
	
	public OsuApiClient(AbstractApiAuthorization auth, RequestBundle bundle) {
		endpoints = EndpointManager.createInstance(this);
		authorization = auth;
		svc = new OsuApiClientInternal(bundle, authorization);
	}

	public synchronized void updateAuthorization(AbstractApiAuthorization newAuth) {
		this.authorization = newAuth;
		svc.updateAuthorization(newAuth);
		ensureAccessToken();
	}

	public void requiresUser() {
		if (authorization instanceof ClientCredentialsGrant) {
			throw new IllegalStateException("The method called must use Authorization Code Grant");
		}
	}
	
	public synchronized void ensureAccessToken() {
		if (authorization.getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		if (!authorization.isStatus()) {
			authorization.authorizationFlow(svc);
		} else {
			authorization.refreshAccessToken(svc);
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
	
	@SuppressWarnings("unchecked")
	public <T> T getJson(String url, HttpMethod... methods) {
		ensureAccessToken();
		HttpMethod method = ClientUtil.optDefault(methods, HttpMethod.GET);
		ResponseEntity<T> entity = (ResponseEntity<T>) svc.genericGetJson(url, method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
		}
		LOG.info("Request Successful");
		return entity.getBody();
	}
}
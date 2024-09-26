package osuapi.client;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import osuapi.client.authorization.RequestBundle;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.endpoints.EndpointManager;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public final EndpointManager endpoints;
	private final ApiAuthorizationInternal authorization; 
	protected final OsuApiClientInternal svc;
	
	public OsuApiClient(ApiAuthorizationInternal grant) {
		this(grant, new RequestBundle());
		
	}
	
	public OsuApiClient(ApiAuthorizationInternal grant, RequestBundle bundle) {
		endpoints = EndpointManager.createInstance(this);
		authorization = grant;
		svc = new OsuApiClientInternal(bundle, authorization);
	}
	
	public synchronized void ensureAccessToken() {
		if (authorization.getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		authorization.authorizationFlow(svc);
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, T target, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, target, methods));
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, Map<String, Object> queryParams, T target, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, target, methods));
	}
	
	public <T> T getJson(String url, Map<String, Object> queryParams, T target, HttpMethod... methods) {
		return getJson(url + ClientUtil.buildQueryString(queryParams), target, methods);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getJson(String url, T target, HttpMethod... methods) {
		ensureAccessToken();
		HttpMethod method = ClientUtil.optDefault(methods, HttpMethod.GET);
		ResponseEntity<T> entity = (ResponseEntity<T>) svc.genericGetJson(url, target.getClass(), method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
		}
		LOG.info("Request Successful");
		return entity.getBody();
	}
}
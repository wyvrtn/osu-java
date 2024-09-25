package osuapi.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.client.resources.RequestBundle;
import osuapi.endpoints.EndpointManager;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public final EndpointManager endpoints;
	private final ApiAuthorizationInternal authorization; 
	protected final OsuApiClientInternal svc;
	
	public OsuApiClient(ClientCredentialsGrant grant) {
		this(grant, new RequestBundle());
		
	}
	
	public OsuApiClient(ClientCredentialsGrant grant, RequestBundle bundle) {
		endpoints = EndpointManager.createInstance(this);
		authorization = grant;
		svc = new OsuApiClientInternal(bundle, authorization);
	}
	
	public synchronized void ensureAccessToken() {
		if (authorization.getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		if (authorization instanceof ClientCredentialsGrant) {
			((ClientCredentialsGrant) authorization).authorizationFlow(svc);
		}
		if (authorization instanceof AuthorizationCodeGrant) {
			((AuthorizationCodeGrant) authorization).authorizationFlow(svc);
		}
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, T target, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, target, methods));
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, Map<String, Object> queryParams, T target, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, queryParams, target, methods));
	}
	
	public <T> T getJson(String url, Map<String, Object> queryParams, T target, HttpMethod... methods) {
		return getJson(url+buildQueryString(queryParams), target, methods);
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
	
	public String buildQueryString(Map<String, Object> params) {
		StringBuilder out = new StringBuilder("");
		params.entrySet().stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
			out.append(String.format("&%s=", encode(entry.getKey())));
			final Object value = entry.getValue();
			if (value instanceof Enum) {
				out.append(ClientUtil.getDescription((Enum<?>) value));
			} else if (value instanceof LocalDateTime) {
				out.append(((LocalDateTime) value).toString());
			} else {
				out.append(encode(value.toString()));
			}
		});
		out.deleteCharAt(0);
		return new String(out);
	}
	
	private static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return " ";
	}
}

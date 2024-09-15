package osuapi.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import osuapi.client.resources.ApiAuth;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.endpoints.EndpointManager;
import osuapi.models.AccessTokenResponse;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public final EndpointManager endpoints;
	private ApiAuth authorization;
	private OsuApiClientInternal svc;
	
	public OsuApiClient(int clientId, String clientSecret) {
		this(Integer.toString(clientId), clientSecret);
	}
	
	private OsuApiClient(String clientId, String clientSecret) {
		endpoints = EndpointManager.createInstance(this);
		authorization = ApiAuth.createInstance();
		authorization.update(clientId, clientSecret);
	}

	public synchronized void ensureAccessToken() {
		LOG.info("Ensuring Valid Access Token");
		if (authorization.getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		CompletableFuture<String> authBody = encodeFormUrl(authorization.getAuthorizationBody());
		CompletableFuture.runAsync(()-> {
			try {
				// Request a new access token and parses the JSON in the response into a response object.
				AccessTokenResponse apResponse = ClientUtil.exceptCoalesce(
						svc.requestNewToken(authBody.get()),
						new OsuApiException("An error occured while requesting a new access token. (response is null)"));
				// Validate the parsed JSON object.
				if (apResponse.getAccessToken()==null || apResponse.getExpiresIn()==0) {
					// Error fields are most likely specified
			        throw new OsuApiException("An error occured while requesting a "
			        		+ "new access token: " + apResponse.getErrorDescription() 
			        		+ " (" + apResponse.getErrorCode() + ").");
				}
				// Updates the expiration date.
				authorization.setAccessToken(apResponse.getAccessToken());
				authorization.setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
					.plusSeconds(apResponse.getExpiresIn() - 30L /** Leniency */));
				LOG.info(authorization.getAccessToken());
			} catch (InterruptedException interrupt) {
				Thread.currentThread().interrupt();
			} catch (Exception e) {
				try {
					throw new OsuApiException("An error occured while requesting a new access token.", e);
				} catch (OsuApiException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public <T> CompletableFuture<T> getJsonAsync(String url, T target, HttpMethod... methods) {
		return CompletableFuture.supplyAsync(() -> getJson(url, target, methods));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getJson(String url, T target, HttpMethod... methods) {
		ensureAccessToken();
		T response = null;
		HttpMethod method = methods.length==0? HttpMethod.GET : methods[0];
		ResponseEntity<T> entity = (ResponseEntity<T>) svc.genericGetJson(url, target.getClass(), method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			try {
				throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
			} catch (OsuApiException e) {
				e.printStackTrace();
			}
		} else {
			response = entity.getBody();
		}
		return response;
	}
	
	private CompletableFuture<String> encodeFormUrl(Map<String, String> params) {
		return CompletableFuture.supplyAsync(() -> {
				String result = "";
				try {
					result = ClientUtil.toFormUrl(params);
				} catch (UnsupportedEncodingException e) {
					LOG.error("Thread: {}, Authorization Body in {} is invalid",
							Thread.currentThread().getName(), this);
					e.printStackTrace();
				}
				return result;
		});
	}
	
	public String buildQueryString(Map<String, Object> params) {
		StringBuilder out = new StringBuilder("");
		Object value;
		for (Entry<String, Object> entry : params.entrySet().stream().filter(entry -> entry.getValue()!=null).collect(Collectors.toList())) {
			out.append(String.format("&%s=", encode(entry.getKey())));
			value = entry.getValue();
			if (value instanceof Enum) {
				out.append(ClientUtil.getDescription((Enum<?>) value));
			} else if (value instanceof LocalDateTime) {
				out.append(((LocalDateTime) value).toString());
			} else {
				out.append(encode(value.toString()));
			}
		}
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

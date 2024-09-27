package osuapi.client;

import java.util.Collections;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import osuapi.client.authorization.RequestBundle;
import osuapi.models.authorization.ApiAuthorizationResponse;
import osuapi.models.authorization.AuthorizationCodeResponse;

public final class OsuApiClientInternal {
    private static final Logger LOG = LoggerFactory.getLogger(OsuApiClientInternal.class);
	private static final String ROOT = "/api/v2";
	private static final String REQTOKEN = "/oauth/token";
	private static final String AUTH = "/oauth/authorize";

	private RestTemplate restTemplate;
	private AbstractApiAuthorization authorization;

	protected OsuApiClientInternal(RequestBundle bundle, AbstractApiAuthorization auth) {
		this.restTemplate = bundle.getApiRestTemplate();
		this.authorization = auth;
	}

	protected void requestAuthorization(String authBody) {
		restTemplate.exchange(AUTH + authBody, HttpMethod.GET, null, Void.class);
	}

	protected AuthorizationCodeResponse exchangeCode(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<AuthorizationCodeResponse> response = restTemplate.exchange(
				REQTOKEN, HttpMethod.POST, requestEntity, AuthorizationCodeResponse.class);
		return Objects.requireNonNull(response.getBody(), "An error occured while exchanging code for an access token. (response is null)");
	}

	protected ApiAuthorizationResponse requestNewToken(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<ApiAuthorizationResponse> response = restTemplate.exchange(
				REQTOKEN, HttpMethod.POST, requestEntity, ApiAuthorizationResponse.class);
		return Objects.requireNonNull(response.getBody(), "An error occured while requesting a new access token. (response is null)");
	}

	protected <T> ResponseEntity<? extends Object> genericGetJson(String url, Class<T> target, HttpMethod method) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + authorization.getAccessToken());
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		LOG.debug("osu-api side request url: {}", url);
		LOG.debug("Http request method: {}", method);
		LOG.debug("Request Entity: {}", headers);
		LOG.debug("Response Class: {}" , target.getSimpleName());
		return restTemplate.exchange(ROOT + url, method, requestEntity, target);
	}
}
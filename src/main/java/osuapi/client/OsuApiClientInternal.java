package osuapi.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import osuapi.client.resources.RequestBundle;
import osuapi.models.authentication.ClientCredentialsResponse;

public final class OsuApiClientInternal {
    private static final Logger LOG = LoggerFactory.getLogger(OsuApiClientInternal.class);
	private static final String ROOT = "/api/v2";
	private static final String AUTH = "/oauth/token";

	private RestTemplate restTemplate;
	private ApiAuthorizationInternal authorization;

	protected OsuApiClientInternal(RequestBundle bundle, ApiAuthorizationInternal auth) {
		this.restTemplate = bundle.getApiRestTemplate();
		this.authorization = auth;
	}

	protected ClientCredentialsResponse requestNewToken(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<ClientCredentialsResponse> response = restTemplate.exchange(
				AUTH, HttpMethod.POST, requestEntity, ClientCredentialsResponse.class);
		return response.getBody();
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
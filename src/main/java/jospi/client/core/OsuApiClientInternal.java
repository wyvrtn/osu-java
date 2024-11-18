package jospi.client.core;

import java.util.Collections;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jospi.client.authorization.HttpServiceProvider;
import jospi.client.request.RequestBundle;
import jospi.models.authorization.ApiAuthorizationResponse;
import jospi.models.authorization.AuthorizationCodeResponse;

public final class OsuApiClientInternal extends HttpServiceProvider {
    private static final Logger LOG = LoggerFactory.getLogger(OsuApiClientInternal.class);
	private static final String ROOT = "/api/v2";
	private static final String REQTOKEN = "/oauth/token";
	private static final String AUTH = "/oauth/authorize";

    private RestTemplate restTemplate;
	private AbstractApiAuthorizationContainer authorization;

    protected OsuApiClientInternal(RequestBundle bundle, AbstractApiAuthorizationContainer auth) {
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

	protected <T extends ApiAuthorizationResponse> T requestNewToken(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<T> response = restTemplate.exchange(
				REQTOKEN, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<T>(){});
		return Objects.requireNonNull(response.getBody(), "An error occured while requesting a new access token. (response is null)");
	}

	protected <T> ResponseEntity<T> genericGetJson(String url, HttpMethod method) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + authorization.getInstance().getAccessToken());
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		LOG.debug("osu-api side request url: {}", url);
		LOG.debug("Http request method: {}", method);
		LOG.debug("Request Entity: {}", headers);
		return restTemplate.exchange(ROOT + url, method, requestEntity, new ParameterizedTypeReference<T>(){});
	}
}
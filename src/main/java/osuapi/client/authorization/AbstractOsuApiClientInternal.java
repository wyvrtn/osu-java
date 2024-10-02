package osuapi.client.authorization;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import osuapi.client.AbstractApiAuthorization;
import osuapi.models.authorization.ApiAuthorizationResponse;
import osuapi.models.authorization.AuthorizationCodeResponse;

public abstract class AbstractOsuApiClientInternal {

    protected RestTemplate restTemplate;
	protected AbstractApiAuthorization authorization;

    protected AbstractOsuApiClientInternal(RequestBundle bundle, AbstractApiAuthorization auth) {
		this.restTemplate = bundle.getApiRestTemplate();
		this.authorization = auth;
	}

    protected abstract void requestAuthorization(String authBody);

    protected abstract AuthorizationCodeResponse exchangeCode(String authBody);

    protected abstract <T extends ApiAuthorizationResponse> T requestNewToken(String authBody);

    protected abstract <T> ResponseEntity<T> genericGetJson(String url, HttpMethod method);
}

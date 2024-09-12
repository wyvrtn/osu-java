package osuapi.svc;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import osuapi.models.AccessTokenResponse;

public interface OsuApiService {
	public AccessTokenResponse requestNewToken(String authBody);
	public <T> ResponseEntity<? extends Object> genericGetJson(String url, Class<T> target, HttpMethod method);
}

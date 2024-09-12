package osuapi.svc;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import osuapi.client.ApiAuth;
import osuapi.models.AccessTokenResponse;

@Service
public class OsuApiServiceImpl implements OsuApiService {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiServiceImpl.class);
	
	@Autowired
	@Qualifier("apiRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("accessToken")
	private ApiAuth accessToken;
	
	@Value("${api.root}")
	private String root;
	
	@Value("${api.auth}")
	private String auth;

	@Override
	public AccessTokenResponse requestNewToken(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(
				auth, HttpMethod.POST, requestEntity, AccessTokenResponse.class);
		return response.getBody();
	}

	@Override
	public <T> ResponseEntity<? extends Object> genericGetJson(String url, Class<T> target, HttpMethod method) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + accessToken.getAccessToken());
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		LOG.debug("osu-api side request url: {}", url);
		LOG.debug("Http request method: {}", method);
		LOG.debug("Request Entity: {}", headers);
		LOG.debug("Response Class: {}" , target.getSimpleName());
		return restTemplate.exchange(root + url, method, requestEntity, target);
	}
}

package osuapi.client;

public final class OsuApiClientInternal {
    private static final Logger LOG = LoggerFactory.getLogger(OsuApiServiceImpl.class);
	private static final String root = "/api/v2";
	private static final String auth = "/oauth/token";

	private RestTemplate restTemplate;
	private String token;

	protected OsuApiClientInternal(RequestBundle bundle, ApiAuth apiAuth) {
		this.restTemplate = bundle.getApiRestTemplate();
		this.token = apiAuth.getAccessToken();
	}

	protected AccessTokenResponse requestNewToken(String authBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> requestEntity = new HttpEntity<>(authBody, headers);
		LOG.debug("Request Entity: {}", headers);
		ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(
				auth, HttpMethod.POST, requestEntity, AccessTokenResponse.class);
		return response.getBody();
	}

	protected <T> ResponseEntity<? extends Object> genericGetJson(String url, Class<T> target, HttpMethod method) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + token.getAccessToken());
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		LOG.debug("osu-api side request url: {}", url);
		LOG.debug("Http request method: {}", method);
		LOG.debug("Request Entity: {}", headers);
		LOG.debug("Response Class: {}" , target.getSimpleName());
		return restTemplate.exchange(root + url, method, requestEntity, target);
	}
}
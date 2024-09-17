package osuapi.client.resources;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ApiAuth {
	private static final Logger LOG = LoggerFactory.getLogger(ApiAuth.class);
	private final Map<String, String> authorizationBody = new HashMap<>();
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	
	public ApiAuth() {
		this ("0", "");
	}
	
	public ApiAuth(String clientId, String clientSecret) {
		authorizationBody.put("client_id", clientId);
		authorizationBody.put("client_secret", clientSecret);
		authorizationBody.put("grant_type", "client_credentials");
		authorizationBody.put("scope", "public");
		LOG.info("New instance of '{}' created by Thread: '{}'", ApiAuth.class.getName(), Thread.currentThread().getName());
	}
	
	public static ApiAuth createInstance() {
		return new ApiAuth();
	}
	
	public static ApiAuth createInstance(String clientId, String clientSecret) {
		return new ApiAuth(clientId, clientSecret);
	}
	
	public void update(String clientId, String clientSecret) {
		authorizationBody.clear();
		authorizationBody.put("client_id", clientId);
		authorizationBody.put("client_secret", clientSecret);
		authorizationBody.put("grant_type", "client_credentials");
		authorizationBody.put("scope", "public");
	}
	
	@Override
	public String toString() {
		return "ApiAuth [accessToken=" + accessToken + ", expirationDate=" + expirationDate + "]";
	}
}

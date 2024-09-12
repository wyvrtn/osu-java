package osuapi.client;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import osuapi.framework.driver.GlobalVariables;

@Getter
@Setter(AccessLevel.PROTECTED)
public final class ApiAuth {
	private static final Logger LOG = LoggerFactory.getLogger(ApiAuth.class);

	private final Map<String, String> authorizationBody = new HashMap<>();
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	
	public ApiAuth() {
		authorizationBody.put("client_id", "0");
		authorizationBody.put("client_secret", "");
		authorizationBody.put("grant_type", "client_credentials");
		authorizationBody.put("scope", "public");
		LOG.info("Authentication initialized by: {}", Thread.currentThread().getName());
	}
	
	public static ApiAuth createInstance() {
		return new ApiAuth();
	}
	
	protected void update(String clientId, String clientSecret) {
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

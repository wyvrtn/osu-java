package osuapi.client;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import osuapi.client.resources.ClientUtil;

@Getter
@Setter(AccessLevel.PROTECTED)
abstract class ApiAuthorizationInternal {
	protected final Logger LOG;
	
	protected final Map<String, String> authorizationBody = new HashMap<>();
	
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	
	protected ApiAuthorizationInternal(Class<? extends ApiAuthorizationInternal> inheritingClass) {
		LOG = LoggerFactory.getLogger(inheritingClass);
	}
	
	protected CompletableFuture<String> encodeFormUrl(Map<String, String> params) {
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
	
	protected abstract void authorizationFlow(OsuApiClientInternal svc);

	protected abstract void refreshAccessToken(OsuApiClientInternal svc);
	
	@Override
	public String toString() {
		return "ApiAuthorization [accessToken=" + accessToken + ", expirationDate=" + expirationDate + "]";
	}
}

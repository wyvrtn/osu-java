package jospi.client.core;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jospi.client.authorization.HttpServiceProvider;
import jospi.client.resources.ClientUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractApiAuthorization {
	protected final Logger LOG;
	
	protected final Map<String, String> authorizationBody = new HashMap<>();
	
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	private boolean status = false;
	
	protected AbstractApiAuthorization(Class<? extends AbstractApiAuthorization> inheritingClass) {
		LOG = LoggerFactory.getLogger(inheritingClass);
	}
	
	protected String encodeFormUrl(Map<String, String> params) {
		String result = "";
		try {
			result = ClientUtil.toFormUrl(params);
		} catch (UnsupportedEncodingException e) {
			LOG.error("Thread: {}, Authorization Body in {} is invalid",
					Thread.currentThread().getName(), this);
			e.printStackTrace();
		}
		return result;
	}
	
	protected abstract void authorizationFlow(HttpServiceProvider svc);

	protected abstract void refreshAccessToken(HttpServiceProvider svc);
	
	@Override
	public String toString() {
		return "ApiAuthorization [accessToken=" + accessToken + ", expirationDate=" + expirationDate + "]";
	}
}

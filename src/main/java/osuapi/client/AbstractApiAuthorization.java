package osuapi.client;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import osuapi.client.authorization.AbstractOsuApiClientInternal;
import osuapi.client.resources.ClientUtil;

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
	
	protected abstract void authorizationFlow(AbstractOsuApiClientInternal svc);

	protected abstract void refreshAccessToken(AbstractOsuApiClientInternal svc);
	
	@Override
	public String toString() {
		return "ApiAuthorization [accessToken=" + accessToken + ", expirationDate=" + expirationDate + "]";
	}
}

package jospi.client.core;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import jospi.client.authorization.HttpServiceProvider;
import jospi.client.resources.ClientUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractApiAuthorization {
	
	protected final Map<String, String> authorizationBody = new HashMap<>();
	
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	private boolean status = false;
	
	protected String encodeFormUrl(Map<String, String> params) {
		String result = "";
		try {
			result = ClientUtil.toFormUrl(params);
		} catch (UnsupportedEncodingException e) {
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

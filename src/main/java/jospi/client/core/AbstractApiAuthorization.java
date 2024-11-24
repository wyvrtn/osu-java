package jospi.client.core;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jospi.client.authorization.StatefulHttpServiceProvider;
import jospi.client.request.NetIOUtilities;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractApiAuthorization implements NetIOUtilities {
	
	protected final Map<String, String> authorizationBody = new ConcurrentHashMap<>();
	
	private String accessToken = "";
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	private boolean status;
	
	protected abstract void authorizationFlow(StatefulHttpServiceProvider svc);

	protected abstract void refreshAccessToken(StatefulHttpServiceProvider svc);
	
	@Override
	public String toString() {
		return "ApiAuthorization [accessToken=" + accessToken + ", expirationDate=" + expirationDate + "]";
	}
}

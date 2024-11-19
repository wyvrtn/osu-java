package jospi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import jospi.client.core.AbstractApiAuthorization;
import jospi.models.authorization.ClientCredentialsResponse;

public final class ClientCredentialsGrant extends AbstractApiAuthorization {
	
	public ClientCredentialsGrant(int clientId, String clientSecret) {
		this(Integer.toString(clientId), clientSecret);
	}
	
	public ClientCredentialsGrant(String clientId, String clientSecret) {
		authorizationBody.put("client_id", clientId);
		authorizationBody.put("client_secret", clientSecret);
		authorizationBody.put("grant_type", "client_credentials");
		authorizationBody.put("scope", "public");
		setStatus(true);
	}
	
	protected synchronized void authorizationFlow(HttpServiceProvider svc) {
		String authBody = super.encodeFormUrl(authorizationBody);
		ClientCredentialsResponse apResponse = (ClientCredentialsResponse) svc.requestNewToken(authBody);
		apResponse.validation();
		setAccessToken(apResponse.getAccessToken());
		setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
			.plusSeconds(apResponse.getExpiresIn() - 30L /** Leniency */));
	}

	protected void refreshAccessToken(HttpServiceProvider svc) {
		authorizationFlow(svc);
	}
}
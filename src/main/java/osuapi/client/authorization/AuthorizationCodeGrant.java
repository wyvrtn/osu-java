package osuapi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import lombok.Getter;
import osuapi.client.AbstractApiAuthorization;
import osuapi.models.authorization.AuthorizationCodeResponse;

@Getter
public class AuthorizationCodeGrant extends AbstractApiAuthorization {
	
	private String clientId;
	private String clientSecret;
	private String refreshToken;

    public AuthorizationCodeGrant(int clientId, String clientSecret, String code, String redirectUri) {
        super(AuthorizationCodeGrant.class);
        this.clientId = Integer.toString(clientId);
        this.clientSecret = clientSecret;
        authorizationBody.put("client_id", this.clientId);
		authorizationBody.put("client_secret", this.clientSecret);
		authorizationBody.put("code", code);
		authorizationBody.put("grant_type", "authorization_code");
        authorizationBody.put("redirect_uri", redirectUri);
		LOG.info("New Instance of {} created in Thread {}", 
				this.getClass().getName(), Thread.currentThread().getName());
    }

    @Override
    protected void authorizationFlow(AbstractOsuApiClientInternal svc) {
    	String authBody = super.encodeFormUrl(authorizationBody);
		AuthorizationCodeResponse acResponse = svc.exchangeCode(authBody);
		acResponse.validation();
		setAccessToken(acResponse.getAccessToken());
		setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
			.plusSeconds(acResponse.getExpiresIn() - 30L /** Leniency */));
		refreshToken = acResponse.getRefreshToken();
		LOG.info(getAccessToken());
		setStatus(true);
    }

	protected void refreshAccessToken(AbstractOsuApiClientInternal svc) {
		authorizationBody.clear();
		authorizationBody.put("client_id", clientId);
		authorizationBody.put("client_secret", clientSecret);
		authorizationBody.put("grant_type", "refresh_token");
		authorizationBody.put("refresh_token", refreshToken);
		
		String authBody = super.encodeFormUrl(authorizationBody);
		AuthorizationCodeResponse acResponse = (AuthorizationCodeResponse) svc.requestNewToken(authBody);
		acResponse.validation();
		setAccessToken(acResponse.getAccessToken());
		setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
			.plusSeconds(acResponse.getExpiresIn() - 30L /** Leniency */));
		refreshToken = acResponse.getRefreshToken();
	}
}
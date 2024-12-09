package jospi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import jospi.client.core.AbstractApiAuthorization;
import jospi.models.authorization.AuthorizationCodeResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true, includeFieldNames=true)
public class AuthorizationCodeGrant extends AbstractApiAuthorization {

    private String clientId;
    private String clientSecret;
    private String refreshToken;

    public AuthorizationCodeGrant(int clientId, String clientSecret, String code, String redirectUri) {
        this.clientId = Integer.toString(clientId);
        this.clientSecret = clientSecret;
        getAuthorizationBody().put("client_id", this.clientId);
        getAuthorizationBody().put("client_secret", this.clientSecret);
        getAuthorizationBody().put("code", code);
        getAuthorizationBody().put("grant_type", "authorization_code");
        getAuthorizationBody().put("redirect_uri", redirectUri);
    }

    protected void authorizationFlow(StatefulHttpServiceProvider svc) {
        String authBody = toFormUrl(getAuthorizationBody());
        processResponse(svc.exchangeCode(authBody));
    }

    protected void refreshAccessToken(StatefulHttpServiceProvider svc) {
        getAuthorizationBody().clear();
        getAuthorizationBody().put("client_id", clientId);
        getAuthorizationBody().put("client_secret", clientSecret);
        getAuthorizationBody().put("grant_type", "refresh_token");
        getAuthorizationBody().put("refresh_token", refreshToken);
        String authBody = toFormUrl(getAuthorizationBody());
        processResponse((AuthorizationCodeResponse) svc.requestNewToken(authBody, AuthorizationCodeResponse.class));
    }

    private void processResponse(AuthorizationCodeResponse response) {
        response.validation();
        setAccessToken(response.getAccessToken());
        setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
            .plusSeconds(response.getExpiresIn() - 30L /** Leniency */));
        refreshToken = response.getRefreshToken();
    }
}

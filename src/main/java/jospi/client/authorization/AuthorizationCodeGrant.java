package jospi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import jospi.client.core.AbstractApiAuthorization;
import jospi.models.authorization.AuthorizationCodeResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true, includeFieldNames = true)
public final class AuthorizationCodeGrant extends AbstractApiAuthorization {

    /**
    * Stores the clientId passed into the constructor for later use.
    */
    private final String clientId;

    /**
    * Stores the clientSecret passed into the constructor for later use.
    */
    private final String clientSecret;

    /**
    * Stores the refreshToken for later reauthorization.
    */
    private String refreshToken;

    /**
    * Constructor of this {@link AuthorizationCodeGrant class}.
    *
    * @param clientId     Client application's id
    * @param clientSecret Client application's secret
    * @param code         One-use code used to exchange an access token
    * @param redirectUri  Redirect URI (URL) used by the client application
    */
    public AuthorizationCodeGrant(final int clientId, final String clientSecret, final String code, final String redirectUri) {
        this.clientId = Integer.toString(clientId);
        this.clientSecret = clientSecret;
        getAuthorizationBody().put("client_id", this.clientId);
        getAuthorizationBody().put("client_secret", this.clientSecret);
        getAuthorizationBody().put("code", code);
        getAuthorizationBody().put("grant_type", "authorization_code");
        getAuthorizationBody().put("redirect_uri", redirectUri);
    }


    /**
    * Exchanges one-use code for access token.
    *
    * @param svc A {@code HttpServiceProvider} that will fulfill the HTTP request
    */
    protected void authorizationFlow(final StatefulHttpServiceProvider svc) {
        String authBody = toFormUrl(getAuthorizationBody());
        processResponse(svc.exchangeCode(authBody));
    }

    /**
    * Refreshes the access token using the {@link jospi.client.authorization.AuthorizationCodeGrant#getRefreshToken() refresh token}.
    *
    * @param svc A {@code HttpServiceProvider} that will fulfill the HTTP request
    */
    protected void refreshAccessToken(final StatefulHttpServiceProvider svc) {
        getAuthorizationBody().clear();
        getAuthorizationBody().put("client_id", clientId);
        getAuthorizationBody().put("client_secret", clientSecret);
        getAuthorizationBody().put("grant_type", "refresh_token");
        getAuthorizationBody().put("refresh_token", refreshToken);
        String authBody = toFormUrl(getAuthorizationBody());
        processResponse(svc.requestNewToken(authBody, AuthorizationCodeResponse.class));
    }

    private void processResponse(final AuthorizationCodeResponse response) {
        response.validation();
        setAccessToken(response.getAccessToken());
        setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
            .plusSeconds(response.getExpiresIn() - LENIENCY));
        refreshToken = response.getRefreshToken();
    }
}

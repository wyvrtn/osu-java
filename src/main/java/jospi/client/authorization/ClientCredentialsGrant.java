package jospi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import jospi.client.core.AbstractApiAuthorization;
import jospi.models.authorization.ClientCredentialsResponse;
import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = true)
public final class ClientCredentialsGrant extends AbstractApiAuthorization {

    public ClientCredentialsGrant(final int clientId, final String clientSecret) {
        this(Integer.toString(clientId), clientSecret);
    }

    public ClientCredentialsGrant(final String clientId, final String clientSecret) {
        getAuthorizationBody().put("client_id", clientId);
        getAuthorizationBody().put("client_secret", clientSecret);
        getAuthorizationBody().put("grant_type", "client_credentials");
        getAuthorizationBody().put("scope", "public");
        setStatus(true);
    }

    protected void authorizationFlow(final StatefulHttpServiceProvider svc) {
        String authBody = toFormUrl(getAuthorizationBody());
        ClientCredentialsResponse apResponse = svc.requestNewToken(authBody, ClientCredentialsResponse.class);
        apResponse.validation();
        setAccessToken(apResponse.getAccessToken());
        setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
            .plusSeconds(apResponse.getExpiresIn() - LENIENCY));
    }

    protected void refreshAccessToken(final StatefulHttpServiceProvider svc) {
        authorizationFlow(svc);
    }
}

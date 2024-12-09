package jospi.client.authorization;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import jospi.client.core.AbstractApiAuthorization;
import jospi.models.authorization.ClientCredentialsResponse;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
public final class ClientCredentialsGrant extends AbstractApiAuthorization {

    public ClientCredentialsGrant(int clientId, String clientSecret) {
        this(Integer.toString(clientId), clientSecret);
    }

    public ClientCredentialsGrant(String clientId, String clientSecret) {
        getAuthorizationBody().put("client_id", clientId);
        getAuthorizationBody().put("client_secret", clientSecret);
        getAuthorizationBody().put("grant_type", "client_credentials");
        getAuthorizationBody().put("scope", "public");
        setStatus(true);
    }

    protected void authorizationFlow(StatefulHttpServiceProvider svc) {
        String authBody = toFormUrl(getAuthorizationBody());
        ClientCredentialsResponse apResponse = (ClientCredentialsResponse) svc.requestNewToken(authBody, ClientCredentialsResponse.class);
        apResponse.validation();
        setAccessToken(apResponse.getAccessToken());
        setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
            .plusSeconds(apResponse.getExpiresIn() - 30L /** Leniency */));
    }

    protected void refreshAccessToken(StatefulHttpServiceProvider svc) {
        authorizationFlow(svc);
    }
}

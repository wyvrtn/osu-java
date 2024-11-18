package jospi.client.authorization;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import jospi.models.authorization.ApiAuthorizationResponse;
import jospi.models.authorization.AuthorizationCodeResponse;

public abstract class HttpServiceProvider {
    protected abstract void requestAuthorization(String authBody);

    protected abstract AuthorizationCodeResponse exchangeCode(String authBody);

    protected abstract <T extends ApiAuthorizationResponse> T requestNewToken(String authBody);

    protected abstract <T> ResponseEntity<T> genericGetJson(String url, HttpMethod method);
}

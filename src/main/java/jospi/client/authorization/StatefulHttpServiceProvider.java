package jospi.client.authorization;

import java.io.IOException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import jospi.client.request.HttpMethod;
import jospi.client.request.HttpRequest;
import jospi.client.request.NetIOUtilities;
import jospi.client.request.RequestBundle;
import jospi.models.authorization.ApiAuthorizationResponse;
import jospi.models.authorization.AuthorizationCodeResponse;

public abstract class StatefulHttpServiceProvider implements NetIOUtilities {
    protected static final String GATEWAY = "https://osu.ppy.sh";
    protected static final String ROOT = "/api/v2";
    protected static final String REQTOKEN = "/oauth/token";
    protected static final String AUTH = "/oauth/authorize";

    public static void requestAuthorization(String authBody) {
        HttpRequest request = new HttpRequest(HttpMethod.GET, AUTH + authBody);
        CloseableHttpClient httpClient = RequestBundle.getDefaultClient();
        try {
            httpClient.execute(request, response -> null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract AuthorizationCodeResponse exchangeCode(String authBody);

    protected abstract <T extends ApiAuthorizationResponse> T requestNewToken(String authBody);

    protected abstract <T> T genericGetJson(String url, HttpMethod method);
}

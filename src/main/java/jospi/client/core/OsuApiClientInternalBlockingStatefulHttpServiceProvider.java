package jospi.client.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jospi.client.authorization.StatefulHttpServiceProvider;
import jospi.client.request.HttpMethod;
import jospi.client.request.HttpRequest;
import jospi.client.request.RequestBundle;
import jospi.models.authorization.ApiAuthorizationResponse;
import jospi.models.authorization.AuthorizationCodeResponse;

public final class OsuApiClientInternalBlockingStatefulHttpServiceProvider extends StatefulHttpServiceProvider {
    private final CloseableHttpClient httpClient;
    private final AbstractApiAuthorizationContainer authorization;
    private ObjectMapper converter;

    protected OsuApiClientInternalBlockingStatefulHttpServiceProvider(RequestBundle bundle, AbstractApiAuthorizationContainer auth) {
        this.httpClient = bundle.getHttpClient();
        this.authorization = auth;
        this.converter = new ObjectMapper();
    }

    protected AuthorizationCodeResponse exchangeCode(String authBody) {
            return Objects.requireNonNull(requestNewToken(authBody, AuthorizationCodeResponse.class), "An error occured while exchanging code for an access token. (response is null)");
    }

    protected <T extends ApiAuthorizationResponse> T requestNewToken(String authBody, Class<T> clazz) {
        HttpRequest request = new HttpRequest(HttpMethod.POST, buildUri(REQTOKEN));
        request.setEntity(new StringEntity(authBody, ContentType.APPLICATION_FORM_URLENCODED));
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        T response = simpleJsonRequest(request, clazz);
        return Objects.requireNonNull(response, "An error occured while requesting a new access token. (response is null)");
    }

    public <T> T genericGetJson(String url, HttpMethod method, Class<T> clazz) {
        HttpRequest request = new HttpRequest(method, buildUri(ROOT, url));
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authorization.getInstance().getAccessToken());
        T response = simpleJsonRequest(request, clazz);
        return Objects.requireNonNull(response, "null object response");
    }

    public <T> T genericGetJson(String url, HttpMethod method, TypeReference<T> typeReference) {
        HttpRequest request = new HttpRequest(method, buildUri(ROOT, url));
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authorization.getInstance().getAccessToken());
        T response = simpleJsonRequest(request, typeReference);
        return Objects.requireNonNull(response, "null object response");
    }

    private <T> T simpleJsonRequest(HttpRequest request, Class<T> clazz) {
        T response = null;
        try {
            response = httpClient.execute(request, httpResponse -> {
                String content = readInputStream(httpResponse.getEntity().getContent());
                return converter.readValue(content, clazz);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private <T> T simpleJsonRequest(HttpRequest request, TypeReference<T> typeReference) {
        T response = null;
        try {
            response = httpClient.execute(request, httpResponse -> {
                String content = readInputStream(httpResponse.getEntity().getContent());
                return converter.readValue(content, typeReference);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String buildUri(String... pathArgs) {
        StringBuilder sb = new StringBuilder(GATEWAY);
        for (String arg : pathArgs) sb.append(arg);
        return new String(sb);
    }

    private String readInputStream(InputStream inputStream) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            for (int length; (length = inputStream.read(buffer)) != -1;) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

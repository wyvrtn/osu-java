package jospi.client.request;

import java.net.URI;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;

public class HttpRequest extends HttpUriRequestBase {

    private static final long serialVersionUID = 1L;

    public HttpRequest(HttpMethod method, URI requestUri) {
        super(method.name(), requestUri);
    }

    public HttpRequest(HttpMethod method, String requestUri) {
        this(method, URI.create(requestUri));
    }
}

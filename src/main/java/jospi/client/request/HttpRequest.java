package jospi.client.request;

import java.net.URI;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;

public class HttpRequest extends HttpUriRequestBase {

    private static final long serialVersionUID = 404L;

    public HttpRequest(final HttpMethod method, final URI requestUri) {
        super(method.name(), requestUri);
    }

    public HttpRequest(final HttpMethod method, final String requestUri) {
        this(method, URI.create(requestUri));
    }
}

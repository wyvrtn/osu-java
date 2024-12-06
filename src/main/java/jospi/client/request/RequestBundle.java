package jospi.client.request;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import lombok.Getter;

@Getter
public final class RequestBundle {
    private static CloseableHttpClient defaultClient;
    private static final Object LOCK = new Object();

    private RequestProperties properties;
    private CloseableHttpClient httpClient;

    public RequestBundle() {
        this(1000, 1000);
    }

    public RequestBundle(int readTimeout, int connectTimeout) {
        properties = RequestProperties.createInstance(readTimeout, connectTimeout);
        RequestConfig config = RequestConfig.custom()
                .setResponseTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .setConnectionRequestTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .build();
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();
    }

    public static CloseableHttpClient getDefaultClient() {
        synchronized(LOCK) {
            if (defaultClient==null) {
                RequestConfig config = RequestConfig.custom()
                        .setResponseTimeout(1000, TimeUnit.MILLISECONDS)
                        .setConnectionRequestTimeout(1000, TimeUnit.MILLISECONDS)
                        .build();
                defaultClient = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .build();
                return defaultClient;
            } else return defaultClient;
        }
    }
}

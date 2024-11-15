package osuapi.client.request;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.Getter;

@Getter
public final class RequestBundle {
    private RequestProperties properties;
    private RestTemplate apiRestTemplate;
    
    public RequestBundle() {
    	this(1000, 1000);
    }
    
    public RequestBundle(int readTimeout, int connectTimeout) {
        properties = RequestProperties.createInstance(readTimeout, connectTimeout);
        apiRestTemplate = new RestTemplate();
        apiRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(RequestProperties.getGATEWAY()));
        HttpComponentsClientHttpRequestFactory rf =
            (HttpComponentsClientHttpRequestFactory) apiRestTemplate.getRequestFactory();
        rf.setReadTimeout(properties.getReadTimeout());
        rf.setConnectTimeout(properties.getConnectTimeout());
    }
}
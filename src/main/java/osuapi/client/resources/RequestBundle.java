package osuapi.client.resources;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
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
        apiRestTemplate = (new RestTemplateBuilder()).uriTemplateHandler(new DefaultUriBuilderFactory(RequestProperties.getGATEWAY()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .build();
    }
}
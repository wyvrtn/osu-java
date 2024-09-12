package osuapi.client;

import lombok.Getter;

@Getter
public final class RequestBundle {
    private RequestProperties properties = RequestProperties.createInstance("https://osu.ppy.sh", 1000, 1000);
    private RestTemplate apiRestTemplate;

    public RequestBundle(String gateway, int readTimeout, int connectTimeout) {
        properties = RequestProperties(gateway, readTimeout, connectTimeout);
        apiRestTemplate = (new RestTemplateBuilder()).uriTemplateHandler(new DefaultUriBuilderFactory(properties.getGateway()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .build();
    }

    public void setBundle(String gateway, int readTimeout, int connectTimeout) {
        properties = RequestProperties(gateway, readTimeout, connectTimeout);
        apiRestTemplate = (new RestTemplateBuilder()).uriTemplateHandler(new DefaultUriBuilderFactory(properties.getGateway()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .build();
    }
}
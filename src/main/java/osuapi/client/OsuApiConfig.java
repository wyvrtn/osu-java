package osuapi.client;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@ComponentScan("osuapi")
public class OsuApiConfig {
	
	@Bean(name="apiRestTemplate")
	public RestTemplate apiRestTemplate(RestTemplateBuilder builder, RequestProperties reqProps) {
		return builder.uriTemplateHandler(new DefaultUriBuilderFactory(reqProps.getGateway()))
                .setReadTimeout(Duration.ofMillis(reqProps.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(reqProps.getConnectTimeout()))
                .build();
	}
	
	@Bean
	public SingletonAccessToken accessToken() {
		return SingletonAccessToken.getInstance();
	}
}

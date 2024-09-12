package osuapi.client;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@ComponentScan("osuapi")
public class OsuApiConfig {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiConfig.class);
	
	@Bean(name="apiRestTemplate")
	public RestTemplate apiRestTemplate(RestTemplateBuilder builder, RequestProperties reqProps) {
		LOG.debug("RestTemplate Built By: {}", Thread.currentThread().getName());
		return builder.uriTemplateHandler(new DefaultUriBuilderFactory(reqProps.getGateway()))
                .setReadTimeout(Duration.ofMillis(reqProps.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(reqProps.getConnectTimeout()))
                .build();
	}
	
	@Bean
	public OsuApiClient setup() {
		LOG.debug("Instance of OsuApiClient Created By: {}", Thread.currentThread().getName());
		return new OsuApiClient(0, "This is totally my client secret");
	}
	@Bean(name="accessToken")
	public ApiAuth accessToken() {
		LOG.debug("Instance of SingletonAccessToken Created By: {}", Thread.currentThread().getName());
		return new ApiAuth();
	}
}

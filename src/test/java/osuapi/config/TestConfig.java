package osuapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import osuapi.client.OsuApiClient;

@Configuration
@ComponentScan("osuapi")
public class TestConfig {
	@Bean
	public OsuApiClient osuApiClient() {
		return new OsuApiClient(1, "secret");
	}
}

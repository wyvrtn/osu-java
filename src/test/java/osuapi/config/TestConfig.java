package osuapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import osuapi.client.authorization.ClientCredentialsGrant;
import osuapi.client.core.OsuApiClient;

@Configuration
@ComponentScan("osuapi")
public class TestConfig {
	@Bean
	public OsuApiClient osuApiClient() {
		ClientCredentialsGrant grant = new ClientCredentialsGrant(1, "secret");
		return new OsuApiClient(grant);
	}
}

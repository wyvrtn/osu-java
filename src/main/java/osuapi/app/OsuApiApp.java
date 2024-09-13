package osuapi.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import osuapi.client.OsuApiClient;

@SpringBootApplication
@Import(OsuApiApp.class)
public class OsuApiApp {
	public static void main(String[] args) {
		OsuApiClient client = new OsuApiClient(1, "secret");
		System.out.println(client);
		//Add later SpringApplication.run(OsuApiApp.class); Avoid SonarLint
	}
	
}

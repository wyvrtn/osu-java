package osuapi.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OsuApiApp.class)
public class OsuApiApp {
	public static void main(String[] args) {
		//Add later SpringApplication.run(OsuApiApp.class); Avoid SonarLint
	}
	
}

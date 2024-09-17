package osuapi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import osuapi.config.TestConfig;

@SpringBootApplication
@Import(TestConfig.class)
public class TestApp {
	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}
	
}

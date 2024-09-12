package osuapi.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import osuapi.client.OsuApiClient;
import osuapi.client.OsuApiConfig;
import osuapi.framework.driver.GlobalVariables;
import osuapi.framework.exception.OsuApiException;

@SuppressWarnings("unused")
@SpringBootApplication
@Import(OsuApiConfig.class)
public class OsuApiApp {
	public static void main(String[] args) {
		OsuApiClient.create();
		//Add later SpringApplication.run(OsuApiApp.class); Avoid SonarLint
	}
	
}

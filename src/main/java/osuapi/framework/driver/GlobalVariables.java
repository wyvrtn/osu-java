package osuapi.framework.driver;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.Setter;
import osuapi.client.ApiAuth;
import osuapi.client.OsuApiClient;
import osuapi.client.RequestProperties;

public final class GlobalVariables {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalVariables.class);
	protected static final Map<String, Field> registry = new HashMap<>();
	protected static GlobalVariables instance;
	private static final AccessGrant grant = new AccessGrant();
	
	private static ApiAuth apiAuthentication;
	
	@Setter
	private static RestTemplate apiRestTemplate;
	
	@Setter
	private static RequestProperties properties;
	
	private static OsuApiClient client;
	
	private GlobalVariables() {
		initalizeStaticFields();
		List<Field> initialVariables = Arrays.asList(this.getClass().getDeclaredFields());
		Map<String, Field> entries = initialVariables.stream().collect(Collectors.toMap(Field::getName, field -> field));
		registry.put("authentication", entries.get("apiAuthentication"));
		registry.put("api_rest_template", entries.get("apiRestTemplate"));
		registry.put("properties", entries.get("properties"));
		registry.put("client", entries.get("client"));
	}
	
	public static synchronized void init(Boolean debug) {
		if (instance==null) {
			instance = new GlobalVariables();
			InjectionDriver.driver.extractYamlProperties("default_path");
			if(Boolean.TRUE.equals(debug)) {
				String message = instance.toString();
				LOG.info(message);
			}
		} else {
			throw new  ExceptionInInitializerError(GlobalVariables.class.getName() + " cannot be initalized twice");
		}
	}
	
	private static void initalizeStaticFields() {
		if (!OsuApiClient.getStatus()) OsuApiClient.create();
		client = OsuApiClient.get();
		apiAuthentication = ApiAuth.getInstance(grant);
		defaultProperties();
		apiRestTemplate = (new RestTemplateBuilder()).uriTemplateHandler(new DefaultUriBuilderFactory(properties.getGateway()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .build();
	}
	
	private static void defaultProperties() {
		properties = RequestProperties.createInstance();
		properties.setGateway("https://osu.ppy.sh");
		properties.setReadTimeout(1000);
		properties.setConnectTimeout(1000);
	}

	@Override
	public String toString() {
		return "GlobalVariables [registry=" + registry + ", APIAuthentication=" + apiAuthentication 
				+ ", apiRestTemplate=" + apiRestTemplate + ", properties=" + properties + ", client=" 
				+ client + "]";
	}

	public static final class AccessGrant {
		private AccessGrant() {}
	}
}

package osuapi.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import osuapi.framework.injection.YamlProperties;

@Component
@YamlProperties(prefix = "api",name = "properties", using = RequestProperties.class)
@ConfigurationProperties(prefix = "api")
@Data
public class RequestProperties {
	private String gateway;
	private int readTimeout;
	private int connectTimeout;
	

	private RequestProperties(String gateway, int readTimeout, int connectTimeout) {
		this.gateway = gateway;
		this.readTimeout = readTimeout;
		this.connectTimeout = connectTimeout;
	}

	public static RequestProperties createInstance(String gateway, int readTimeout, int connectTimeout) {
		return new RequestProperties(gateway, readTimeout, connectTimeout);
	}
}

package osuapi.models.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "api")
@Data
public class RequestProperties {
	private String gateway;
	private int readTimeout;
	private int connectTimeout;
}

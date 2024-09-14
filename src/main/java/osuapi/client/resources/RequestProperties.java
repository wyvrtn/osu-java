package osuapi.client.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestProperties {
	private static final Logger LOG = LoggerFactory.getLogger(RequestProperties.class);
	@Getter
	private static final String gateway = "https://osu.ppy.sh";
	private int readTimeout;
	private int connectTimeout;
	

	private RequestProperties(int readTimeout, int connectTimeout) {
		this.readTimeout = readTimeout;
		this.connectTimeout = connectTimeout;
	}

	public static RequestProperties createInstance(int readTimeout, int connectTimeout) {
		LOG.info("New instance of RequestProperties has been created by: {}", Thread.currentThread().getName());
		return new RequestProperties(readTimeout, connectTimeout);
	}
}

package jospi.client.request;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestProperties {
	@Getter
	private static final String GATEWAY = "https://osu.ppy.sh";

	private int readTimeout;
	private int connectTimeout;
	

	private RequestProperties(int readTimeout, int connectTimeout) {
		this.readTimeout = readTimeout;
		this.connectTimeout = connectTimeout;
	}

	public static RequestProperties createInstance(int readTimeout, int connectTimeout) {
		return new RequestProperties(readTimeout, connectTimeout);
	}
}

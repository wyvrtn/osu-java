package osuapi.client.resources;

public class OsuApiException extends Exception {
	private static final long serialVersionUID = 8596965465781109261L;

	public OsuApiException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public OsuApiException(String message) {
		super(message);
	}
}

package osuapi.models.custom;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingletonAccessToken {
	private static final Logger LOG = LoggerFactory.getLogger(SingletonAccessToken.class);
	private static SingletonAccessToken instance;
	
	private String accessToken;
	private OffsetDateTime expirationDate = OffsetDateTime.MIN;
	
	private SingletonAccessToken() {
		LOG.info("{} initialized Access Token", Thread.currentThread().getName());
	}
	
	public static synchronized SingletonAccessToken getInstance() {
		if (instance==null) {
			instance = new SingletonAccessToken();
		}
		return instance;
	}
}

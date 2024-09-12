package osuapi.client;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import osuapi.endpoints.EndpointManager;
import osuapi.framework.driver.GlobalVariables;
import osuapi.framework.exception.OsuApiException;
import osuapi.framework.injection.OsuApiInject;
import osuapi.models.AccessTokenResponse;
import osuapi.svc.OsuApiService;

public final class OsuApiClient {
	private static final Logger LOG = LoggerFactory.getLogger(OsuApiClient.class);
	public EndpointManager endpoints;
	private ApiAuth authorization;
	private OsuApiClientInternal svc;
	
	public OsuApiClient(int clientId, String clientSecret) {
		this(Integer.toString(clientId), clientSecret);
	}
	
	private OsuApiClient(String clientId, String clientSecret) {
		ednpoints = EndpointManager.createInstance();
		authorization = ApiAuth.createInstance();
		authorization.update(clientId, clientSecret);
	}

	public synchronized void ensureAccessToken() throws OsuApiException {
		LOG.info("Ensuring Valid Access Token");
		if (authorization.getExpirationDate().isAfter(OffsetDateTime.now())) {
			return;
		}
		try {
			 // Request a new access token and parses the JSON in the response into a response object.
			String authBody = "";
			for (Entry<String, String> entry : authorization.getAuthorizationBody().entrySet()) {
				authBody = buildString(authBody, entry.getKey(), "=", entry.getValue(), "&");
			}
			StringUtils.chop(authBody);
			LOG.info("Authorization Query String: {}", authBody);
			AccessTokenResponse apResponse = svc.requestNewToken(authBody);
			// Validate the parsed JSON object.
			if (apResponse==null) {
				throw new OsuApiException("An error occured while requesting"
						+ " a new access token. (response is null)");
			}
			if (apResponse.getAccessToken()==null || apResponse.getExpiresIn()==0) {
				// Error fields are most likely specified
		        throw new OsuApiException("An error occured while requesting a "
		        		+ "new access token: " + apResponse.getErrorDescription() 
		        		+ " (" + apResponse.getErrorCode() + ").");
			}
			// Updates the expiration date.
			authorization.setAccessToken(apResponse.getAccessToken());
			authorization.setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
				.plusSeconds(apResponse.getExpiresIn() - 30L /** Leniency */));
			LOG.info(authorization.getAccessToken());
			//This is not needed for now LOG.info(accessToken.getExpirationDate().toString()); Sonar Escaper
		} catch (Exception e) {
			throw new OsuApiException("An error occured while requesting a new access token.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getJson(String url, T target, HttpMethod... methods) throws OsuApiException {
		ensureAccessToken();
		T response = null;
		HttpMethod method = methods.length==0? HttpMethod.GET : methods[0];
		ResponseEntity<T> entity = (ResponseEntity<T>) svc.genericGetJson(url, target.getClass(), method);
		if (entity.getStatusCode()!=HttpStatus.OK) {
			throw new OsuApiException("Request Did Not Receive HTTP Status Code 200");
		} else {
			response = entity.getBody();
		}
		return response;
	}
	
	public String buildQueryString(Map<String, Object> params) throws UnsupportedEncodingException {
		String out = "?";
		String key = "";
		String value = "";
		for (Entry<String, Object> entry : params.entrySet()) {
			key = URLEncoder.encode(entry.getKey(), "UTF-8");
			value = entry.getValue().toString();
			if (entry.getValue() instanceof Enum) {
				for (Field f : value.getClass().getDeclaredFields()) {
					if (f.getName().equals("description")) {
						out = buildString(out, key, "=", invokeGetter(f, value).toString(), "&");
					}
				}
			} else if (entry.getValue() instanceof LocalDateTime) {
				out = buildString(out, key, "=", value, "&");
			} else {
				value = URLEncoder.encode(value, "UTF-8");
				out = buildString(out, key, "=", value, "&");
			}
		}
		return StringUtils.chop(out);
	}
	
	private Object invokeGetter(Field field, Object o) {
	    for (Method method : o.getClass().getMethods()) {
	        if (method.getName().startsWith("get")&&(method.getName().length()
	        		==field.getName().length() + 3)) {
	        	try {
	        		return method.invoke(o);
	        	}
	            catch (IllegalAccessException|InvocationTargetException e) {
	            	LOG.error("Could not determine method: {}", method.getName());
	            }
	        }
	    }
	    return " ";
	}
	
	private String buildString(String...strArgs) {
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<strArgs.length; i++) {
			builder.append(strArgs[i]);
		}
		return builder.toString();
	}
}

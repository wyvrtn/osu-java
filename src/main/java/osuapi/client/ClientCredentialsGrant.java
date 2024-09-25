package osuapi.client;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.concurrent.CompletableFuture;

import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.models.authentication.ClientCredentialsResponse;

public final class ClientCredentialsGrant extends ApiAuthorizationInternal {
	
	public ClientCredentialsGrant(int clientId, String clientSecret) {
		this(Integer.toString(clientId), clientSecret);
	}
	
	public ClientCredentialsGrant(String clientId, String clientSecret) {
		super(ClientCredentialsGrant.class);
		authorizationBody.put("client_id", clientId);
		authorizationBody.put("client_secret", clientSecret);
		authorizationBody.put("grant_type", "client_credentials");
		authorizationBody.put("scope", "public");
		LOG.info("New Instance of {} created in Thread {}", 
				this.getClass().getName(), Thread.currentThread().getName());
	}
	
	protected synchronized void authorizationFlow(OsuApiClientInternal svc) {
		CompletableFuture<String> authBody = super.encodeFormUrl(authorizationBody);
		try {
			// Request a new access token and parses the JSON in the response into a response object.
			ClientCredentialsResponse apResponse = ClientUtil.exceptCoalesce(
				svc.requestNewToken(authBody.get()),
				new OsuApiException("An error occured while requesting a new access token. (response is null)"));
			// Validate the parsed JSON object.
			if (apResponse.getAccessToken()==null || apResponse.getExpiresIn()==0) {
				// Error fields are most likely specified
			       throw new OsuApiException("An error occured while requesting a "
			        + "new access token: " + apResponse.getErrorDescription() 
			        + " (" + apResponse.getErrorCode() + ").");
			}
			// Updates the expiration date.
			setAccessToken(apResponse.getAccessToken());
			setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
				.plusSeconds(apResponse.getExpiresIn() - 30L /** Leniency */));
			LOG.info(getAccessToken());
		} catch (InterruptedException interrupt) {
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

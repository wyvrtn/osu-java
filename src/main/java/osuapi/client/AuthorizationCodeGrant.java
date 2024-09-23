package osuapi.client;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.concurrent.CompletableFuture;

import lombok.Getter;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.models.authentication.ClientCredentialsResponse;

@Getter
public class AuthorizationCodeGrant extends ApiAuthorizationInternal {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
	private String queuedKey;

    protected AuthorizationCodeGrant(String clientId, String clientSecret, String redirectUri, String state, String... scopes) {
        super(AuthorizationCodeGrant.class);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        authorizationBody.put("client_id", this.clientId);
		authorizationBody.put("redirect_uri", this.redirectUri);
		authorizationBody.put("response_type", "code");
		authorizationBody.put("scope", String.join(" ", scopes));
        authorizationBody.put("state", state);
		LOG.info("New Instance of {} created in Thread {}", 
				this.getClass().getName(), Thread.currentThread().getName());
    }

    @Override
    protected void authorizationFlow(OsuApiClientInternal svc) {
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

    @Override
    protected synchronized void update(String... args) {
        if (args.length!=3 || args.length!=4) return;
        authorizationBody.clear();
        authorizationBody.put("client_id", args[0]);
		authorizationBody.put("redirect_uri", args[1]);
		authorizationBody.put("response_type", "code");
		authorizationBody.put("scope", args.length==4? args[3] : "");
        authorizationBody.put("state", args[2]);
    }

    public void update(int clientId, String redirectUri, String state, String... scopes) {
        this.update(Integer.toString(clientId), redirectUri, state, String.join(" ", scopes));
    }
}

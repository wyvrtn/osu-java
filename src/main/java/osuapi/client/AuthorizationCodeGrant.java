package osuapi.client;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import lombok.Getter;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.models.authentication.AuthorizationCodeResponse;
import osuapi.models.authentication.QueuedAuthorizationCodeContainer;
import osuapi.models.authentication.QueuedAuthorizationCodeContainer.ContainerStatus;;

@Getter
public class AuthorizationCodeGrant extends ApiAuthorizationInternal {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
	private AuthorizationCodeKey queuedKey;

	private String refreshToken;

	private Map<String, String> tokenBody = new HashMap<>();

	public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri) {
		this(clientId, clientSecret, redirectUri, "");
	}

	public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri, String state) {
		this (clientId, clientSecret, redirectUri, state, new String[1]);
	}

    public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri, String state, String[] scopes) {
        super(AuthorizationCodeGrant.class);
        this.clientId = Integer.toString(clientId);
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
		this.queuedKey = new AuthorizationCodeKey(this);
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
		AuthorizationCodeQueue.queueContainer(queuedKey);
		QueuedAuthorizationCodeContainer queuedContainer = AuthorizationCodeQueue.lookupQueuedContainer(queuedKey);
        try {
			svc.requestAuthorization(authBody.get(), this.redirectUri);
			CompletableFuture<ContainerStatus> status = CompletableFuture.supplyAsync(() ->
				queuedContainer.awaitResponse(1000, 30)
			);
			if (status.get().equals(ContainerStatus.SUCCESS)) {
				setExchangeCodeBody(queuedContainer.getCode());
				retrieveToken(svc);
			} else return;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private void retrieveToken(OsuApiClientInternal svc) {
		CompletableFuture<String> authBody = super.encodeFormUrl(tokenBody);
		try {
			AuthorizationCodeResponse acResponse = ClientUtil.exceptCoalesce(
				svc.exchangeCode(authBody.get()),
				new OsuApiException("An error occured while requesting a new access token. (response is null)"));
			// Validate the parsed JSON object.
			if (acResponse.getAccessToken()==null || acResponse.getExpiresIn()==0) {
				// Error fields are most likely specified
			       throw new OsuApiException("An error occured while exchanging code for accessToken");
			}
			// Updates the expiration date.
			setAccessToken(acResponse.getAccessToken());
			setExpirationDate(OffsetDateTime.now(ZoneId.systemDefault())
				.plusSeconds(acResponse.getExpiresIn() - 30L /** Leniency */));
			this.refreshToken = acResponse.getRefreshToken();
			LOG.info(getAccessToken());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void refreshAccessToken(OsuApiClientInternal svc) {
		setRefreshTokenBody();
		retrieveToken(svc);
	}

	private void setExchangeCodeBody(String code) {
		tokenBody.put("client_id", this.clientId);
		tokenBody.put("client_secret", this.clientSecret);
		tokenBody.put("code", code);
		tokenBody.put("grant_type", "authorization_code");
        tokenBody.put("redirect_uri", this.redirectUri);
	}

	private void setRefreshTokenBody() {
		tokenBody.put("client_id", this.clientId);
		tokenBody.put("client_secret", this.clientSecret);
		tokenBody.put("grant_type", "refresh_token");
		tokenBody.put("refresh_token", this.refreshToken);
	}
}
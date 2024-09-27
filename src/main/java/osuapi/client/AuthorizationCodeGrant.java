package osuapi.client;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.concurrent.CompletableFuture;

import lombok.Getter;
import osuapi.client.authorization.AuthorizationCodeKey;
import osuapi.client.authorization.AuthorizationCodeQueue;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;
import osuapi.models.authentication.AuthorizationCodeFields;
import osuapi.models.authentication.AuthorizationCodeResponse;
import osuapi.models.authentication.AuthorizationCodeStage;
import osuapi.models.authentication.QueuedAuthorizationCodeContainer;
import osuapi.models.authentication.QueuedAuthorizationCodeContainer.ContainerStatus;;

@Getter
public class AuthorizationCodeGrant extends AbstractApiAuthorization {

	private AuthorizationCodeFields fields;
	private AuthorizationCodeKey queuedKey;
	private AuthorizationCodeStage stage = AuthorizationCodeStage.REQUEST_AUTHORIZATION;

	public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri) {
		this(clientId, clientSecret, redirectUri, "");
	}

	public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri, String state) {
		this(clientId, clientSecret, redirectUri, state, new String[1]);
	}

    public AuthorizationCodeGrant(int clientId, String clientSecret, String redirectUri, String state, String[] scopes) {
        super(AuthorizationCodeGrant.class);
		this.fields = new AuthorizationCodeFields();
        this.fields.setClientId(Integer.toString(clientId));
        this.fields.setClientSecret(clientSecret);
        this.fields.setRedirectUri(redirectUri);
		this.fields.setState(state);
		this.fields.setScopes(scopes);
		this.queuedKey = new AuthorizationCodeKey(this);
        this.authorizationStageOne();
		LOG.info("New Instance of {} created in Thread {}", 
				this.getClass().getName(), Thread.currentThread().getName());
    }

    @Override
    protected void authorizationFlow(OsuApiClientInternal svc) {
        CompletableFuture<String> authBody = super.encodeFormUrl(authorizationBody);
		AuthorizationCodeQueue.queueContainer(queuedKey);
		QueuedAuthorizationCodeContainer queuedContainer = AuthorizationCodeQueue.lookupQueuedContainer(queuedKey);
        try {
			svc.requestAuthorization(authBody.get(), fields.getRedirectUri());
			CompletableFuture<ContainerStatus> status = CompletableFuture.supplyAsync(() ->
				queuedContainer.awaitResponse(1000, 30)
			);
			if (status.get().equals(ContainerStatus.SUCCESS)) {
				stage = AuthorizationCodeStage.EXCHANGE_CODE;
				authorizationStageTwo();
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
		CompletableFuture<String> authBody = super.encodeFormUrl(authorizationBody);
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
			fields.setRefreshToken(acResponse.getRefreshToken());
			LOG.info(getAccessToken());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void refreshAccessToken(OsuApiClientInternal svc) {
		stage = AuthorizationCodeStage.REFRESH_TOKEN;
		authorizationStageThree();
		retrieveToken(svc);
	}

	private void authorizationStageOne() {
		authorizationBody.clear();
		authorizationBody.put("client_id", this.fields.getClientId());
		authorizationBody.put("redirect_uri", this.fields.getRedirectUri());
		authorizationBody.put("response_type", "code");
		authorizationBody.put("scope", String.join(" ", this.fields.getScopes()));
        authorizationBody.put("state", this.fields.getState());
	}

	private void authorizationStageTwo() {
		authorizationBody.clear();
		authorizationBody.put("client_id", fields.getClientId());
		authorizationBody.put("client_secret", fields.getClientSecret());
		authorizationBody.put("code", fields.getCode());
		authorizationBody.put("grant_type", "authorization_code");
        authorizationBody.put("redirect_uri", fields.getRedirectUri());
	}

	private void authorizationStageThree() {
		authorizationBody.clear();
		authorizationBody.put("client_id", fields.getClientId());
		authorizationBody.put("client_secret", fields.getClientSecret());
		authorizationBody.put("grant_type", "refresh_token");
		authorizationBody.put("refresh_token", fields.getRefreshToken());
	}
}
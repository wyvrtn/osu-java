package osuapi.models.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osuapi.client.resources.ClientUtil;
import osuapi.client.resources.OsuApiException;

@Getter
@Setter
@NoArgsConstructor
public class ClientCredentialsResponse implements ApiAuthorizationResponse {
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	@JsonProperty("error")
	private String errorCode;
	
	@JsonProperty("error_description")
	private String errorDescription;

	@Override
	public void validation() {
		if (ClientUtil.anyNull(accessToken, expiresIn)) {
			throw new OsuApiException("An error occured while requesting a "
	        + "new access token: " + errorDescription
	        + " (" + errorCode + ").");
		}
	}
}

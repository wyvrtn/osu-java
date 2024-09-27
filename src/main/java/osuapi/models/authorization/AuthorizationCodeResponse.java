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
public class AuthorizationCodeResponse implements ApiAuthorizationResponse {

    @JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	@JsonProperty("token_type")
	private String tokenType;

	@Override
	public void validation() {
		if (ClientUtil.anyNull(accessToken, expiresIn, refreshToken)) {
			throw new OsuApiException("Response Contains Null Values");
		}
	}
}

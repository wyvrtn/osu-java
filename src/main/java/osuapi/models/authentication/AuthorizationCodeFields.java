package osuapi.models.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorizationCodeFields {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String refreshToken;
    private String code;
    private String state;
    private String[] scopes;
}

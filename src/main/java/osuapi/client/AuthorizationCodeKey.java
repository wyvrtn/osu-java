package osuapi.client;

import lombok.Getter;

public class AuthorizationCodeKey {
    private @Getter String queueKey;

    public AuthorizationCodeKey(AuthorizationCodeGrant instance) {
        this.queueKey = Integer.toHexString(instance.hashCode());
    }
}

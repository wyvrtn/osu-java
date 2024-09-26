package osuapi.client.authorization;

import lombok.Getter;
import osuapi.client.AuthorizationCodeGrant;

public class AuthorizationCodeKey {
    private @Getter String queueKey;
    private @Getter AuthorizationCodeGrant tiedInstance;

    public AuthorizationCodeKey(AuthorizationCodeGrant instance) {
        this.queueKey = Integer.toHexString(instance.hashCode());
        this.tiedInstance = instance;
    }

    @Override
    public boolean equals(Object anotherKey) {
        if (anotherKey instanceof AuthorizationCodeKey) {
            AuthorizationCodeKey casted = (AuthorizationCodeKey) anotherKey;
            if (casted.getQueueKey().equals(this.queueKey) && casted.getTiedInstance().equals(this.tiedInstance)) {
                return true;
            }
        }
        return false;
    }
}

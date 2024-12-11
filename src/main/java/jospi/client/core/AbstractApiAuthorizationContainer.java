package jospi.client.core;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public final class AbstractApiAuthorizationContainer implements Serializable {
    private static final long serialVersionUID = 63L;

    private AbstractApiAuthorization instance;

    private AbstractApiAuthorizationContainer(final AbstractApiAuthorization authorization) {
        this.instance = authorization;
    }

    protected static AbstractApiAuthorizationContainer newInstance(final AbstractApiAuthorization authorization) {
        return new AbstractApiAuthorizationContainer(authorization);
    }
}

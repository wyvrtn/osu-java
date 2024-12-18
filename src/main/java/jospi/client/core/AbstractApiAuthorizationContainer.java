package jospi.client.core;

import java.io.Serializable;

import jospi.client.request.RequestBundle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public final class AbstractApiAuthorizationContainer implements Serializable {
    private static final long serialVersionUID = 63L;

    private final RequestBundle bundle;
    private AbstractApiAuthorization instance;

    private AbstractApiAuthorizationContainer(final AbstractApiAuthorization authorization, final RequestBundle bundle) {
        this.instance = authorization;
        this.bundle = bundle;
    }

    protected static AbstractApiAuthorizationContainer newInstance(final AbstractApiAuthorization authorization, final RequestBundle bundle) {
        return new AbstractApiAuthorizationContainer(authorization, bundle);
    }
}

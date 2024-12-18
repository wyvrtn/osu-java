package jospi.client.core;

import java.io.Serializable;

import jospi.client.authorization.HttpServiceProviderType;
import jospi.client.authorization.StatefulHttpServiceProvider;
import jospi.endpoints.AbstractApiEndpoints;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
public abstract class AbstractOsuApiClient implements Serializable {
    private static final long serialVersionUID = 727L;

    private transient AbstractApiEndpoints apiEndpoints;
    private AbstractApiAuthorizationContainer authorization;
    private final transient StatefulHttpServiceProvider svc;

    protected AbstractOsuApiClient(AbstractApiAuthorizationContainer auth, HttpServiceProviderType type) {
        this.authorization = auth;
        switch (type) {
            case DEFAULT:
                svc = new DefaultBlockingStatefulHttpServiceProvider(auth, auth.getBundle());
                break;
            default:
                svc = new DefaultBlockingStatefulHttpServiceProvider(auth, auth.getBundle());
                break;
        }
    }
}

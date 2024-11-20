package jospi.client.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public final class AbstractApiAuthorizationContainer {
    private AbstractApiAuthorization instance;

    private AbstractApiAuthorizationContainer(AbstractApiAuthorization instance) {
        this.instance = instance;
    }

    protected static AbstractApiAuthorizationContainer newInstance(AbstractApiAuthorization instance) {
        return new AbstractApiAuthorizationContainer(instance);
    }
}

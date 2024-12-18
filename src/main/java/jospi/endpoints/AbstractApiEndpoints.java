package jospi.endpoints;

import jospi.client.core.AbstractOsuApiClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@ToString(of = {"client"})
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractApiEndpoints {
    private final AbstractOsuApiClient client;

    protected AbstractApiEndpoints(AbstractOsuApiClient client) {
        this.client = client;
    }
}

package jospi.client.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "createInstance")
public final class RequestProperties {
    private final int readTimeout;
    private final int connectTimeout;
}

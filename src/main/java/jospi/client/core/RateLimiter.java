package jospi.client.core;

import java.io.Closeable;

public interface RateLimiter extends Closeable {
    public boolean request();
    public void initiate();
}

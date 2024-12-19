package jospi.client.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public final class OsuApiRateLimiter implements Cloneable, RateLimiter, Serializable  {
    private static final long serialVersionUID = 823543L; // 7^7
    private static final Object LOCK = new Object();

    private static OsuApiRateLimiter instance;
    private static ScheduledExecutorService executor;
    private static boolean state = false;

    @Getter
    @Setter
    private static int limit = 60;

    private int bucket;

    private OsuApiRateLimiter() {
        this.bucket = OsuApiRateLimiter.limit;
    }

    public static OsuApiRateLimiter getInstance() {
        synchronized (OsuApiRateLimiter.LOCK) {
            if (OsuApiRateLimiter.instance==null) {
                OsuApiRateLimiter.instance = new OsuApiRateLimiter();
                return OsuApiRateLimiter.instance;
            } else {
                return OsuApiRateLimiter.instance;
            }
        }
    }

    public boolean request() {
        synchronized (OsuApiRateLimiter.LOCK) {
            if (bucket==0) {
                return false;
            } else {
                --bucket;
                return true;
            }
        }
    }

    public void initiate() {
        synchronized (OsuApiRateLimiter.LOCK) {
            if (OsuApiRateLimiter.state) {
                return;
            } else {
                if (executor==null) {
                    executor = newExecutor();
                }
                executor.scheduleAtFixedRate(() -> {
                    this.bucket = OsuApiRateLimiter.limit;
                }, 0, 1, TimeUnit.MINUTES);
                OsuApiRateLimiter.state = true;
            }
        }
    }

    private ScheduledExecutorService newExecutor() {
        return new ScheduledThreadPoolExecutor(4);
    }

    @Override
    public void close() throws IOException {
        synchronized (OsuApiRateLimiter.LOCK) {
            OsuApiRateLimiter.state = false;
            executor.shutdown();
            executor = null;
        }
    }
}

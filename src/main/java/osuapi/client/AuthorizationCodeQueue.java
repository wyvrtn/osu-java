package osuapi.client;

import java.util.HashMap;
import java.util.Map;
import osuapi.models.authentication.QueuedAuthorizationCodeContainer;

public abstract class AuthorizationCodeQueue {
    private static final Map<String, QueuedAuthorizationCodeContainer> queue = new HashMap<>();

    public static void queueContainer(String key, String val) {
        queue.put(key, new QueuedAuthorizationCodeContainer(key));
    }
}

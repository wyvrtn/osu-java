package osuapi.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import osuapi.models.authentication.QueuedAuthorizationCodeContainer;

public final class AuthorizationCodeQueue {
    private static final Map<String, QueuedAuthorizationCodeContainer> queue = new HashMap<>();

    private AuthorizationCodeQueue() {
        throw new RuntimeException("YOU ARE A BOZO");
    }

    public static void queueContainer(AuthorizationCodeKey key) {
        queue.put(key.getQueueKey(), new QueuedAuthorizationCodeContainer(key));
    }

    public static QueuedAuthorizationCodeContainer lookupQueuedContainer(String key) {
        for (Entry<String, QueuedAuthorizationCodeContainer> queueEntry : queue.entrySet()) {
            if (queueEntry.getKey().equals(key)) {
                return queueEntry.getValue();
            }
        }
        return null;
    }
}

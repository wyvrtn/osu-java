package osuapi.client.authorization;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import osuapi.models.authentication.QueuedAuthorizationCodeContainer;

public abstract class AuthorizationCodeQueue {
    private static final Map<String, QueuedAuthorizationCodeContainer> queue = new HashMap<>();

    public static final void queueContainer(AuthorizationCodeKey tiedKey) {
        queue.put(tiedKey.getQueueKey(), new QueuedAuthorizationCodeContainer(tiedKey));
    }

    public static final QueuedAuthorizationCodeContainer lookupQueuedContainer(AuthorizationCodeKey tiedKey) {
        return lookupQueuedContainer(tiedKey.getQueueKey());
    }

    public static final QueuedAuthorizationCodeContainer lookupQueuedContainer(String key) {
        for (Entry<String, QueuedAuthorizationCodeContainer> queueEntry : queue.entrySet()) {
            if (queueEntry.getKey().equals(key)) {
                return queueEntry.getValue();
            }
        }
        return null;
    }
}

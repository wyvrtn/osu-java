package jospi.client.resources;

import java.util.Map;

@FunctionalInterface
public interface Dictionary<K, V> {
    void apply(Map<K, V> map);
}

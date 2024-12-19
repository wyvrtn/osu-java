package jospi.client.components;

import java.util.Map;

@FunctionalInterface
public interface Dictionary<K, V> {
    void apply(Map<K, V> map);
}

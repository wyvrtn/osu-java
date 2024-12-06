package jospi.client.resources;

import java.util.Map;

@FunctionalInterface
public interface Dictionary<K,V> {
    public void apply(Map<K,V> map);
}

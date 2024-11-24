package jospi.client.resources;

import java.util.Map;

public interface Dictionary<K,V> extends Map<K,V> {
    public void init(Map<K,V> map);
}

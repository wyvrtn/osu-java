package jospi.client.resources;

import java.util.HashMap;
import java.util.Map;

public abstract class Dictionary<K,V> extends HashMap<K,V> {

	private static final long serialVersionUID = -3743330107512461551L;

	public abstract void create(Map<K,V> map);
}

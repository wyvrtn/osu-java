package jospi.models.records;

import java.util.HashMap;
import java.util.Map;

public interface HttpRecord {
    public default Map<String, Object> convert() {
		return convert(new HashMap<>());
	}
    public Map<String, Object> convert(Map<String, Object> map);
}

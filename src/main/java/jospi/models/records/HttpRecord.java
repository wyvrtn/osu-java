package jospi.models.records;

import java.util.HashMap;
import java.util.Map;

public interface HttpRecord {
    default Map<String, Object> convert() {
        return convert(new HashMap<>());
    }
    Map<String, Object> convert(Map<String, Object> map);
}

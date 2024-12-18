package jospi.models.records;

import jospi.client.resources.Dictionary;

public interface HttpRecord {
    Dictionary<String, Object> convert();
}

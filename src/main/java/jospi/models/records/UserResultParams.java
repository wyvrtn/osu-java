package jospi.models.records;

import jospi.client.resources.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserResultParams implements HttpRecord {
    private final int limit;
    private final int offset;

    public Dictionary<String, Object> convert() {
        return map -> {
            map.put("limit", limit);
            map.put("offset", offset);
        };
    }
}

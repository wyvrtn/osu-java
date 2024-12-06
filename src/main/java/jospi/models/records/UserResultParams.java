package jospi.models.records;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserResultParams implements HttpRecord {
    private final int limit;
    private final int offset;

    public Map<String, Object> convert(Map<String, Object> map) {
        map.put("limit", limit);
        map.put("offset", offset);
        return map;
    }
}

package osuapi.models.structs;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import lombok.Getter;
import lombok.Setter;
import osuapi.models.structs.processors.Struct;

@Getter
@Setter
public abstract class MatchEventParameters extends Struct<MatchEventParameters> {
    private int before;
    private int after;
    private int limit;

    public static MatchEventParameters construct(int before, int after, int limit) {
        return new MatchEventParameters() {
            @Override
            public Class<? extends MatchEventParameters> constructor() {
                setBefore(before);
                setAfter(after);
                setLimit(limit);
                return this.getClass();
            }
        };
    }

    protected abstract Class<? extends MatchEventParameters> constructor();

    public final Pair<List<String>, Object[]> queryProcess() {
        List<String> names = Arrays.asList("before", "after", "limit");
        Object[] values = {before, after, limit};
        return Pair.of(names, values);
    }
}

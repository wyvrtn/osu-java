package osuapi.models.structs;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserParameters {
    private int limit;
    private int offset;

    public static UserParameters construct(int limit, int offset) {
        return new UserParameters() {
            @Override
            public Class<? extends UserParameters> constructor() {

                return this.getClass();
            }
        };
    }

    protected abstract Class<? extends UserParameters> constructor();

    public final Pair<List<String>, Object[]> queryProcess() {
        List<String> names = Arrays.asList("before", "after", "limit");
        Object[] values = {limit, offset};
        return Pair.of(names, values);
    }
}

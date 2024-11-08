package osuapi.models.structs;

import lombok.Getter;
import lombok.Setter;
import osuapi.models.structs.interfaces.QueryProcessable;
import osuapi.models.structs.processors.Struct;

@Getter
@Setter
public abstract class MatchEventParameters extends Struct<MatchEventParameters> implements QueryProcessable {
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

    public final String queryProcess() {
        StringBuilder outSB = new StringBuilder("?");
        String[] names = {"before", "after", "limit"};
        int[] values = {before, after, limit};
        for (int i=0; i<names.length; i++) {
            outSB.append(names[i]);
            outSB.append('=');
            outSB.append(values[i]);
            outSB.append('&');
        }
        outSB.deleteCharAt(outSB.length()-1);
        return new String(outSB);
    }
}

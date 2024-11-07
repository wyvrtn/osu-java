package osuapi.models.structs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MatchEventParams extends Struct implements Constructable, QueryProcessable {
    private int before;
    private int after;
    private int limit;

    public MatchEventParams() {
        super(MatchEventParams.class);
    }

    public abstract void constructor();

    public final String process() {
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

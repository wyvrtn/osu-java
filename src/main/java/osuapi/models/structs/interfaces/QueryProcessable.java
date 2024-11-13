package osuapi.models.structs.interfaces;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public interface QueryProcessable {
    public Pair<List<String>, Object[]> queryProcess();
}

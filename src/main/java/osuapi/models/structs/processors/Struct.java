package osuapi.models.structs.processors;

import osuapi.models.structs.interfaces.QueryProcessable;

public abstract class Struct<T extends Struct<T>> implements QueryProcessable {
    protected abstract Class<? extends T> constructor();
}

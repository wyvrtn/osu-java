package osuapi.models.structs.hierarchy;

public abstract class Struct<T> {
    protected abstract Class<? extends T> constructor();
}

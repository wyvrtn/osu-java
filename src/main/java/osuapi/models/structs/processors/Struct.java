package osuapi.models.structs.processors;

public abstract class Struct<T extends Struct<T>> {
    protected abstract Class<? extends T> constructor();
}

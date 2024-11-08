package osuapi.models.structs.processors;

public abstract class StructProcessor<T extends StructProcessor<T>> {
    protected static final InternalStructProccessorLock LOCK = new InternalStructProccessorLock();

    public StructProcessor(Class<? extends StructProcessor<T>> inheritingClass) {
        throw new InstantiationError("Class " + inheritingClass.getSimpleName() + " cannot be instantiated");
    }

    protected StructProcessor(InternalStructProccessorLock lock) {}

    protected static final class InternalStructProccessorLock {
        private InternalStructProccessorLock() {}
    }

    protected abstract void process(Struct<?> abstractStruct);
}

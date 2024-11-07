package osuapi.models.structs.hierarchy;

public final class StructConstructor {
    public StructConstructor() {
        throw new InstantiationError("Class StructConstructor cannot be instantiated");
    }

    public static final void construct(Struct<?> abstractStruct) {
        Class<?> abstractClass = abstractStruct.constructor();
        if (!abstractClass.isAnonymousClass()) {
            throw new InstantiationError("Abstract class " + abstractStruct.getClass().getSimpleName() + " can only be instantiated by an anonymous inner class");
        }
    }
}

package osuapi.models.structs;

public abstract class Struct {
    public Struct(Class<?> inheritingClass) {
        throw new InstantiationError("Abstract class " + inheritingClass.getSimpleName() + " can only be extended anonymously");
    }
}

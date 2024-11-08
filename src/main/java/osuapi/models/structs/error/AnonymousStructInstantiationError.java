package osuapi.models.structs.error;

import osuapi.models.structs.processors.Struct;

public class AnonymousStructInstantiationError extends InstantiationError {
    public AnonymousStructInstantiationError() {
        super();
    }

    public AnonymousStructInstantiationError(Struct<?> abstractStruct) {
        super("Abstract class " + abstractStruct.getClass().getSimpleName() + " can only be instantiated by an anonymous inner class");
    }
}

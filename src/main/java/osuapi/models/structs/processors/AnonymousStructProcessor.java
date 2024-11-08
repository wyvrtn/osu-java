package osuapi.models.structs.processors;

import osuapi.models.structs.error.AnonymousStructInstantiationError;

public final class AnonymousStructProcessor extends StructProcessor<AnonymousStructProcessor> {
    private static AnonymousStructProcessor instance = new AnonymousStructProcessor(LOCK);

    public AnonymousStructProcessor() {
        super(AnonymousStructProcessor.class);
    }

    private AnonymousStructProcessor(InternalStructProccessorLock lock) {
        super(lock);
    }

    protected void process(Struct<?> abstractStruct) {
        Class<?> abstractClass = abstractStruct.constructor();
        if (!abstractClass.isAnonymousClass()) {
            throw new AnonymousStructInstantiationError(abstractStruct);
        }
    }

    public static void construct(Struct<?> abstractStruct) {
        instance.process(abstractStruct);
    }
}

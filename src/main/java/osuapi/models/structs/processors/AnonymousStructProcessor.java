package osuapi.models.structs.processors;

import osuapi.models.structs.error.AnonymousStructInstantiationError;

public final class AnonymousStructProcessor extends StructProcessor<AnonymousStructProcessor> {

    public AnonymousStructProcessor() {
        super(AnonymousStructProcessor.class);
    }

    protected AnonymousStructProcessor(InternalStructProccessorLock lock) {
        super(lock);
    }

    public void process(Struct<?> abstractStruct) {
        Class<?> abstractClass = abstractStruct.constructor();
        if (!abstractClass.isAnonymousClass()) {
            throw new AnonymousStructInstantiationError(abstractStruct);
        }
    }
}

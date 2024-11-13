package osuapi.models.structs.processors;

import java.lang.reflect.InvocationTargetException;

import osuapi.models.structs.processors.StructProcessor.InternalStructProccessorLock;

public abstract class AbstractStructProcessorFactory {
    public AbstractStructProcessorFactory() {
        throw new InstantiationError("Class " + AbstractStructProcessorFactory.class.getSimpleName() + " cannot be instantiated nor extended by any subclass");
    }

    public static <T extends StructProcessor<T>> T generate(Class<? extends T> concreteClass) {
        T result = null;
        try {
            result = concreteClass.getConstructor(InternalStructProccessorLock.class).newInstance(StructProcessor.LOCK);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }

}

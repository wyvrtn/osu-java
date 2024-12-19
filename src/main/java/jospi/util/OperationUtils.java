package jospi.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public final class OperationUtils {
    private OperationUtils() {
        throw new InstantiationError("Class " + OperationUtils.class.getName() + " cannot be instantiated");
    }

    public static <L, R> Object nullishCoalesce(final L leftArg, final R rightArg) {
        return leftArg == null ? rightArg : leftArg;
    }

    public static void awaitRunnable(final CompletableFuture<Void> runnable) {
        try {
            runnable.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static <T> T awaitTask(final CompletableFuture<T> task) {
        try {
            return task.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

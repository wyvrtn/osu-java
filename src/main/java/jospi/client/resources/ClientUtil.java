package jospi.client.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public final class ClientUtil {
	private ClientUtil() {
		throw new InstantiationError("Class " + ClientUtil.class.getName() + " cannot be instantiated");
	}

	public static Map<String, Object> buildQueryMap(List<String> keys, Object... params) {
		Map<String, Object> result = new HashMap<>();
		if (keys.size()!=params.length) return result;
		for (int index=0; index<keys.size(); index++) result.put(keys.get(index), params[index]);
		return result;
	}
	
	public static <L, R> Object nullishCoalesce(L leftArg, R rightArg) {
		return leftArg==null? rightArg : leftArg;
	}
	
	public static void awaitRunnable(CompletableFuture<Void> runnable) {
		try {
			runnable.get();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> T awaitTask(CompletableFuture<T> task) {
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

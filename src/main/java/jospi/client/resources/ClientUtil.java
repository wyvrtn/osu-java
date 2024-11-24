package jospi.client.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import jospi.enums.DescriptionEnum;

public final class ClientUtil {
	private ClientUtil() {
		throw new InstantiationError("Class " + ClientUtil.class.getName() + " cannot be instantiated");
	}
	
	public static String toFormUrl(Map<String, String> params) throws UnsupportedEncodingException {
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Entry<String, String> entry : params.entrySet()){
	        if (first) first = false;
	        else result.append('&');
	        
	        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	        result.append('=');
	        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    }    
	    return result.toString();
	}

	public static String buildQueryString(Map<String, Object> params) {
		if (params instanceof Dictionary<?, ?>) ((Dictionary<String, Object>) params).init(new HashMap<>());
		StringBuilder out = new StringBuilder("");
		params.entrySet().stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
			out.append(String.format("&%s=", encode(entry.getKey())));
			final Object value = entry.getValue();
			if (value instanceof Enum<?>) {
				if (value instanceof DescriptionEnum) {
					out.append(((DescriptionEnum) value).getDescription());
				} else {
					out.append(value.toString());
				}
			} else if (value instanceof LocalDateTime) {
				out.append(((LocalDateTime) value).toString());
			} else {
				out.append(encode(value.toString()));
			}
		});
		out.deleteCharAt(0);
		return new String(out);
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

	private static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return " ";
	}
}

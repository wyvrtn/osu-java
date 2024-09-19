package osuapi.client.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import osuapi.enums.DescriptionEnum;

public final class ClientUtil {
	private ClientUtil() {
		throw new AssertionError("No osuapi.client.resources.ClientUtil instances for you!");
	}
	
	public static String toFormUrl(Map<String, String> params) throws UnsupportedEncodingException {
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Entry<String, String> entry : params.entrySet()){
	        if (first) first = false;
	        else result.append("&");
	        
	        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    }    
	    return result.toString();
	}
	
	public static <I, X extends Throwable> I exceptCoalesce(I input, X except) throws X {
		return Optional.ofNullable(input).orElseThrow(() -> except);
	}
	
	public static <L, R> Object nullishCoalesce(L leftArg, R rightArg) {
		return leftArg==null? rightArg : leftArg;
	}

	public static <T> T optDefault(T[] givenArgs, T defaultArg) {
		if (givenArgs==null || givenArgs.length>1) {
			throw new IllegalArgumentException("Optional Default Argument Must be Non-Null and Singular");
		} else if (givenArgs.length==0) {
			return defaultArg;
		} else return givenArgs[0];
	} 
	
	public static String getDescription(Enum<?> descriptableEnum) {
		if (descriptableEnum instanceof DescriptionEnum) {
			return ((DescriptionEnum<?>) descriptableEnum).getDescription();
		}
		return descriptableEnum.toString();
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

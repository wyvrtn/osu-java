package osuapi.client.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;

import osuapi.enums.DescriptionEnum;

import java.util.Map.Entry;

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
	
	public static String getDescription(Enum<?> descriptableEnum) {
		if (descriptableEnum instanceof DescriptionEnum) {
			return ((DescriptionEnum<?>) descriptableEnum).getDescription();
		}
		return descriptableEnum.toString();
	}
}

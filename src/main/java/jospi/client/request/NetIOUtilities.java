package jospi.client.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jospi.client.resources.Dictionary;
import jospi.enums.DescriptionEnum;

public interface NetIOUtilities {
	public default String toFormUrl(Map<String, String> params) {
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Entry<String, String> entry : params.entrySet()){
	        if (first) first = false;
	        else result.append('&');
	        
	        result.append(encode(entry.getKey()));
	        result.append('=');
	        result.append(encode(entry.getValue()));
	    }    
	    return result.toString();
	}

	public default String toQueryString(Map<String, Object> params) {
		if (params instanceof Dictionary<?, ?>) ((Dictionary<String, Object>) params).create(new HashMap<>());
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
	
	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return " ";
	}
}

package osuapi.framework.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

public final class YamlUtil {
	
	private static MapOptions options = MapOptions.FLAT;
	
	private YamlUtil() {}
	
	public static MapOptions getOptions() {
		return options;
	}

	public static void setOptions(String options) {
		if (Pattern.matches("^[a-zA-Z]*$", options)) {
			options = org.apache.commons.lang3.StringUtils.upperCase(options);
		} else {
			try {
				throw new IllegalArgumentException("Illegal Argument for " +
						YamlUtil.class.getMethod("setOptions", String.class).getName()
						+ "in class " + YamlUtil.class);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		YamlUtil.options = YamlUtil.MapOptions.valueOf(options);
	}

	public static String readYamlFromFile(String path) {
		String contents = "";
		try (FileReader fileReader = new FileReader(path)) {
			BufferedReader reader = new BufferedReader(fileReader);
			contents = reader.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}
	
	public static Map<String, Object> loadYamlToMap(String path) {
		Yaml yamlReader = new Yaml();
		Map<String, Object> rawMap = yamlReader.load(readYamlFromFile(path));
		if (options==MapOptions.FLAT) return getFlattenedMap(rawMap);
		else return rawMap;
	}
	
	public static Map<String, Object> getFlattenedMap(Map<String, Object> source) {
		Map<String, Object> result = new LinkedHashMap<>();
		buildFlattenedMap(result, source, null);
		return result;
	}
	
	private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String path) {
		source.forEach((key, value) -> {
			if (StringUtils.hasText(path)) {
				if (key.startsWith("[")) {
					key = path + key;
				}
				else {
					key = path + '.' + key;
				}
			}
			if (value instanceof String) {
				result.put(key, value);
			}
			else if (value instanceof Map) {
				// Need a compound key
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) value;
				buildFlattenedMap(result, map, key);
			}
			else if (value instanceof Collection) {
				// Need a compound key
				@SuppressWarnings("unchecked")
				Collection<Object> collection = (Collection<Object>) value;
				if (collection.isEmpty()) {
					result.put(key, "");
				}
				else {
					int count = 0;
					for (Object object : collection) {
						buildFlattenedMap(result, Collections.singletonMap(
								"[" + (count++) + "]", object), key);
					}
				}
			}
			else {
				result.put(key, (value != null ? value : ""));
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> asMap(Object object) {
		// YAML can have numbers as keys
		Map<String, Object> result = new LinkedHashMap<>();
		if (!(object instanceof Map)) {
			// A document can be a text literal
			result.put("document", object);
			return result;
		}

		Map<Object, Object> map = (Map<Object, Object>) object;
		map.forEach((key, value) -> {
			if (value instanceof Map) {
				value = asMap(value);
			}
			if (key instanceof CharSequence) {
				result.put(key.toString(), value);
			}
			else {
				// It has to be a map key in this case
				result.put("[" + key.toString() + "]", value);
			}
		});
		return result;
	}
	
	private enum MapOptions {
		FLAT,
		NESTED
	}
}

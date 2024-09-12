package osuapi.framework.driver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import osuapi.framework.injection.Injector;
import osuapi.framework.injection.YamlProperties;
import osuapi.framework.util.YamlUtil;

@Injector
public final class InjectionDriver {
	
	protected static final InjectionDriver driver = new InjectionDriver();
	
	private InjectionDriver() {}
	
	@SafeVarargs
	public static List<Class<?>> scanForAnnotations(Class<? extends Annotation>... annotations) {
		ClassPathScanningCandidateComponentProvider scanner = 
				new ClassPathScanningCandidateComponentProvider(false);
		
		Arrays.stream(annotations).forEach(annotation -> 
			scanner.addIncludeFilter(new AnnotationTypeFilter(annotation)));
		
		return scanner.findCandidateComponents("osuapi").stream()
				.map(beanDefinition -> {
					try {
						return Class.forName(beanDefinition.getBeanClassName());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					return void.class;
			}).collect(Collectors.toList());
	}
	
	protected void extractYamlProperties(String yamlPath) {
		if (yamlPath.equals("default_path")) {
			yamlPath = "src/main/resources/application.yml";
		}
		Map<String, Object> deserializedYaml = YamlUtil.loadYamlToMap(yamlPath);
		List<Class<?>> candidates  = scanForAnnotations(YamlProperties.class);
		candidates.stream().forEach(clazz -> {
			YamlProperties properties = clazz.getAnnotation(YamlProperties.class);
			Field registeredField = matchByName(properties.name())==null? matchByType(clazz) : matchByName(properties.name());
			Arrays.asList(clazz.getDeclaredFields()).stream().forEach(field -> {
				registeredField.setAccessible(true);
				try {
					registeredField.set(GlobalVariables.instance, deserializedYaml.get(properties.prefix() + "." + registeredField.getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				registeredField.setAccessible(false);
			});
		});
	}
	
	private Field matchByName(String name) {
		return GlobalVariables.registry.get(name);
	}
	
	private Field matchByType(Class<?> clazz) {
		Optional<Field> optField = GlobalVariables.registry.values().stream().filter(field -> field.getType().equals(clazz)).findFirst();
		if (optField.isPresent()) return optField.get();
		return null;
	}
}

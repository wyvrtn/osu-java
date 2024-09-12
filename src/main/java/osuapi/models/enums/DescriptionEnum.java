package osuapi.models.enums;

public interface DescriptionEnum<T> {
	public String getDescription();
	public T getEnum(String input);
}

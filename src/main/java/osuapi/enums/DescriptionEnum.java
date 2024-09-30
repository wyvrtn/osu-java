package osuapi.enums;

public interface DescriptionEnum<E extends Enum<E>> {
	public String getDescription();
	public E getEnum(String input);
}

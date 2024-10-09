package osuapi.models.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CursorResponse<T> {
	private T[] data;
	private String cursorString;
}

package jospi.iterator;

import java.util.Objects;
import java.util.function.Function;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitToken<T> {
    private T token;
    private T next;
    private final Function<T, Boolean> evaluate;

    public ExitToken(T token) {
        this(token, Objects::nonNull);
    }

    public ExitToken(T token, Function<T, Boolean> eval) {
        this.token = token;
        this.evaluate = eval;
    }

    public boolean doExit() {
        return evaluate.apply(token);
    }
}

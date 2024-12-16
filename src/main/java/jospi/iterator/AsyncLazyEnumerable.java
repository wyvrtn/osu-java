package jospi.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import lombok.Getter;

public class AsyncLazyEnumerable<T, R> implements Iterable<CompletableFuture<R>> {
    private final Function<ExitToken<T>, CompletableFuture<R>> enumerator;
    private final ExitToken<T> token;
    private final ExitType type;
    @Getter
    private List<CompletableFuture<R>> cache;

    public AsyncLazyEnumerable(Function<ExitToken<T>, CompletableFuture<R>> func, ExitToken<T> token) {
        this(func, token, ExitType.WHILE);
    }

    public AsyncLazyEnumerable(Function<ExitToken<T>, CompletableFuture<R>> func, ExitToken<T> token, ExitType type) {
        this.enumerator = func;
        this.token = token;
        this.type = type;
    }

    public List<CompletableFuture<R>> asList() {
        List<CompletableFuture<R>> out = new ArrayList<>();
        try {
            while (Boolean.TRUE.equals(moveNextAsync().get())) {
                out.add(current());
                moveNextAsync();
            }
        } catch (InterruptedException | ExecutionException e) {
            if (e.getClass() == InterruptedException.class) {
                Thread.currentThread().interrupt();
            }
            e.printStackTrace();
        }
        return out;
    }

    public <K> AsyncLazyEnumerable<T, K> append(Function<CompletableFuture<R>, CompletableFuture<K>> func) {
        Function<ExitToken<T>, CompletableFuture<K>> appended = enumerator.andThen(func);
        return new AsyncLazyEnumerable<>(appended, token, type);
    }

    public CompletableFuture<R> current() {
        if (type == ExitType.WHILE) {
            if (token.doExit()) {
                return null;
            } else {
                CompletableFuture<R> out = enumerator.apply(token);
                cache.add(out);
                return out;
            }
        } else if (type == ExitType.DO_WHILE) {
            if (type.getState() == 0) {
                type.setState(1);
                CompletableFuture<R> out = enumerator.apply(token);
                cache.add(out);
                return out;
            } else {
                if (token.doExit()) {
                    return null;
                } else {
                    CompletableFuture<R> out = enumerator.apply(token);
                    cache.add(out);
                    return out;
                }
            }
        }
        return null;
    }

    public CompletableFuture<Boolean> moveNextAsync() {
        synchronized (this) {
            if (token.getNext() != null) {
                return CompletableFuture.supplyAsync(() -> {
                        token.setToken(token.getNext());
                        return Boolean.TRUE;
                });
            } else {
                return CompletableFuture.completedFuture(Boolean.FALSE);
            }
        }
    }

    @Override
    public Iterator<CompletableFuture<R>> iterator() {
        return new AsyncLazyEnumerator(this);
    }

    public class AsyncLazyEnumerator implements Iterator<CompletableFuture<R>> {

        private final AsyncLazyEnumerable<T, R> instance;

        public AsyncLazyEnumerator(AsyncLazyEnumerable<T, R> instance) {
            this.instance = instance;
        }

        @Override
        public boolean hasNext() {
            return instance.token.getNext() != null;
        }

        @Override
        public CompletableFuture<R> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            instance.moveNextAsync();
            return instance.current();
        }

    }
}

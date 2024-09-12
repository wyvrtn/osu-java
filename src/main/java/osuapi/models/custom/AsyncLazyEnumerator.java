package osuapi.models.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class AsyncLazyEnumerator<T, TResult> {
    private Function<ExitToken<T>, CompletableFuture<TResult>> iterator;
    private ExitToken<T> token;
    private ExitType type = ExitType.WHILE;
    private List<CompletableFuture<TResult>> cache;
    
    public AsyncLazyEnumerator(Function<ExitToken<T>, CompletableFuture<TResult>> func, ExitToken<T> token) {
        this.iterator = func;
        this.token = token;
    }
    
    public AsyncLazyEnumerator(Function<ExitToken<T>, CompletableFuture<TResult>> func, ExitToken<T> token, ExitType type) {
        this.iterator = func;
        this.token = token;
        this.type = type;
    }
    
    public List<CompletableFuture<TResult>> getCache() {
        return cache;
    }
    
    public List<CompletableFuture<TResult>> asList() {
    	List<CompletableFuture<TResult>> out = new ArrayList<>();
    	try {
			while (Boolean.TRUE.equals(moveNextAsync().get())) {
				out.add(current());
				moveNextAsync();
			}
		} catch (InterruptedException | ExecutionException e) {
			if (e.getClass()==InterruptedException.class) {
			    Thread.currentThread().interrupt();
			}
			e.printStackTrace();
		}
    	return out;
    }
    
    public CompletableFuture<TResult> current() {
        if (type==ExitType.WHILE) {
        	if (token.doExit()) return null;
        	else {
        		CompletableFuture<TResult> out = iterator.apply(token);
                cache.add(out);
                return out;
        	}
        } else if (type==ExitType.DO_WHILE) {
        	if (type.getState()==0) {
                type.setState(1);
                CompletableFuture<TResult> out = iterator.apply(token);
                cache.add(out);
                return out;
            } else {
                if (token.doExit()) return null;
                else {
                    CompletableFuture<TResult> out = iterator.apply(token);
                    cache.add(out);
                    return out;
                }
            }
        }
        return null;
    }
    
    public synchronized CompletableFuture<Boolean> moveNextAsync() {
    	if (token.getNext()!=null) {
    		return CompletableFuture.supplyAsync(() -> {
    				token.setToken(token.getNext());
    				return Boolean.TRUE;
    		});
    	} else {
    		return CompletableFuture.completedFuture(Boolean.FALSE);
    	}
    }
}


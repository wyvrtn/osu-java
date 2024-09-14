package osuapi.iterator;

import java.util.function.Function;

public class ExitToken<T> {
    private T token;
    private T next;
    private Function<T, Boolean> evaluate;
    
    public ExitToken(T token, Function<T, Boolean> eval) {
        this.token = token;
        this.evaluate = eval;
    }
    
    public T getToken() {
        return this.token;
    }
    
    public void setToken(T token) {
        this.token = token;
    }
    
    public T getNext() {
        return this.next;
    }
    
    public void setNext(T next) {
        this.next = next;
    }
    
    public boolean doExit() {
        return evaluate.apply(token);
    }
}


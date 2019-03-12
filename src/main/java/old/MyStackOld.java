package old;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MyStackOld<T> {
    private volatile Object[] arr;
    private volatile AtomicInteger next = new AtomicInteger();
    private volatile AtomicInteger size = new AtomicInteger();

    public MyStackOld() {
        arr = new Object[10];
        next.set(0);
        size.set(arr.length);
    }

    public synchronized T push(T item) {
        if (next.get()==size.get()){
            size.set(size.get()*2);
            arr = Arrays.copyOf(arr, size.get());
        }
        arr[next.get()]=item;
        next.incrementAndGet();
        return (T)arr[next.get()-1];
    }

    public synchronized T pop() {
        if(next.get()-1<0){
            return null;
        }
        return (T)arr[next.decrementAndGet()];
    }

    public synchronized T peek() {
        if(next.get()-1<0){
            return null;
        }
        return (T)arr[next.get()-1];
    }

    public boolean empty() {
        return next.get()==0;
    }

}

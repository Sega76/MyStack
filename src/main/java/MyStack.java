import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class MyStack<T> extends Stack<T> {
    private volatile Object[] arr;
    private volatile AtomicInteger next = new AtomicInteger();
    private volatile AtomicInteger size = new AtomicInteger();

    public MyStack() {
        arr = new Object[10];
        next.set(0);
        size.set(arr.length);
    }

    @Override
    public synchronized T push(T item) {
        if (next.get()==size.get()){
            size.set(size.get()*2);
            arr = Arrays.copyOf(arr, size.get());
        }
        arr[next.get()]=item;
        next.incrementAndGet();
        return (T)arr[next.get()-1];
    }

    @Override
    public synchronized T pop() {
        if(next.get()-1<0){
            return null;
        }
        return (T)arr[next.decrementAndGet()];
//        return super.pop();
    }

    @Override
    public synchronized T peek() {
        if(next.get()-1<0){
            return null;
        }
        return (T)arr[next.get()-1];
    }

    @Override
    public boolean empty() {
        return next.get()==0;
    }

    @Override
    public synchronized int search(Object o) {
        return super.search(o);
    }
}

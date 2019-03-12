import java.util.Arrays;

public class MyStack<T> {
    private Object[] arr;
    private volatile int next;
    private volatile int size;

    public MyStack() {
        arr = new Object[10];
        next=0;
        size=arr.length;
    }

    public synchronized T push(T item) {
            if (next == size) {
                size = size * 2;
                arr = Arrays.copyOf(arr, size);
            }
            arr[next] = item;
            next++;
            return (T) arr[next - 1];
    }

    public synchronized T pop() {
            if (next - 1 < 0) {
                return null;
            }
            next--;
            return (T) arr[next];
    }

    public synchronized T peek() {
        if(next-1<0){
            return null;
        }
        return (T)arr[next-1];
    }

    public boolean empty() {
        return next==0;
    }

    public int getNext() {
        return next;
    }
}

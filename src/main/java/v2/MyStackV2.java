package v2;


import java.util.concurrent.atomic.AtomicReference;

public class MyStackV2<T> {

    public MyStackV2() {
    }

    private final AtomicReference<Node<T>> top = new AtomicReference<>(null);

    public void push(T object) {

        Node<T> newObj = new Node<>(object, null);

        while (true) {

            Node<T> oldObj = top.get();
            newObj.next = oldObj;

            if (top.compareAndSet(oldObj, newObj)) {
                break;
            }
        }
    }
    public T peek() {
        return top.get().value;
    }
    public T pop() {
        while (true) {
            Node<T> oldObj = this.top.get();
            Node<T> newObj = oldObj.next;
            if (top.compareAndSet(oldObj, newObj)) {
                return oldObj.value;
            }
        }
    }
    public boolean empty(){
        return top.get().value==null;
    }

    private class Node<E> {
        private final E value;
        private Node<E> next;
        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
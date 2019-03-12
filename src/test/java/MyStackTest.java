import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyStackTest {
    MyStack<String> myStack;


    @Test
    public void push() {
        int size = 50_000;
        myStack = new MyStack<>();
        intStreamPush(size);
    }

    @Test
    public void pop() {
        int size = 50_000;
        myStack = new MyStack<>();
        intStreamPush(size);
        intStreamPop(size);
    }

    public void intStreamPush(int size) {
        IntStream.range(0, size)
                .parallel()
                .forEach(i -> {
                            myStack.push("line_" + i);
                        }
                );
        System.out.println("next "+ myStack.getNext());
        Assert.assertTrue(myStack.getNext() == size);

    }

    public void intStreamPop(int size) {
        Set<String> set = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> myStack.pop())
                .filter(s -> s != null)
                .collect(Collectors.toSet());
        System.out.println("size: " + set.size());
        Assert.assertTrue(set.size() == size);
    }

}
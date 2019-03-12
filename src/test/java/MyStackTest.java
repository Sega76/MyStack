import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MyStackTest {
    MyStack<String> myStack2;


    @Test
    public void push() {
        int size = 50_000;
        myStack2 = new MyStack<>();
        intStreamPush(size);
    }

    @Test
    public void pop() {
        int size = 50_000;
        myStack2 = new MyStack<>();
        intStreamPush(size);
        intStreamPop(size);
    }

    public void intStreamPush(int size) {
        IntStream.range(0, size)
                .parallel()
                .forEach(i -> {
                            myStack2.push("line_" + i);
                        }
                );

        Assert.assertTrue(myStack2.getNext()==size);

    }

    public void intStreamPop(int size) {
        Set<String> set = IntStream.range(0, size)
                .parallel()
                .mapToObj(t -> myStack2.pop())
                .collect(Collectors.toSet());
        System.out.println("size: " + set.size());
        Assert.assertTrue(set.size() == size);
    }

}
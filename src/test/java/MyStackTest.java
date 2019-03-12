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
        myStack2 = new MyStack<>();
        intStreamPush();
    }

    @Test
    public void pop() {
        myStack2 = new MyStack<>();
        intStreamPush();
        intStreamPop();
    }

    public void intStreamPush() {
        IntStream.range(0, 50)
                .parallel()
                .forEach(i -> {
                            myStack2.push("line_" + i);
//                            System.out.println(i);
                        }
                );
        Assert.assertTrue(myStack2.getNext()==50);

    }

    public void intStreamPop() {
        Set<String> set = IntStream.range(0, 50)
                .parallel()
                .mapToObj(t -> myStack2.pop())
//                .peek(System.out::println)
                .collect(Collectors.toSet());
        System.out.println("set size: " + set.size());
        Assert.assertTrue(set.size() == 50);
    }

}
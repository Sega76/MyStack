package v2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyStackV2Test {
    MyStackV2<String> myStack;


    @Test
    public void pop() {
        int size = 50_000;
        myStack = new MyStackV2();
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
    }

    public void intStreamPop(int size) {
        Set<String> set = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> myStack.pop())
                .filter(s -> s != null)
                .map(Object::toString)
                .collect(Collectors.toSet());


        System.out.println("size: " + set.size());
        Assert.assertTrue(set.size() == size);
    }

}
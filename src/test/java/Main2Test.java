
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main2Test {
    static MyStack2<String> stack;

    static {
        stack = new MyStack2<String>();
    }

    @Test
    public void mainTest() throws InterruptedException {
        intStreamPush();
//        pushTest();
        Thread.sleep(10*1000L);
        System.out.println("next " + stack.getNext());
        intStreamPop();
//        popTest();

    }

    public void intStreamPush() {
        IntStream.range(0, 50)
                .parallel()
                .forEach(i -> {
                            stack.push("line_" + i);
//                            System.out.println(i);
                        }
                );

    }

    public void intStreamPop() {
        Set<String> set = IntStream.range(0, 50)
                .parallel()
                .mapToObj(t -> stack.pop())
                .peek(System.out::println)
                .collect(Collectors.toSet());
        System.out.println("set size: " + set.size());
        Assert.assertTrue(set.size() == 50);
    }

    public void pushTest() {
        System.out.println("pushTest");
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                String s = "strint_"+finalI;
                @Override
                public void run() {
//                    System.out.println(s);
                    stack.push(s);
                }
            }).start();

        }
    }

    public void popTest() {
        System.out.println("popTest");
        for (int i = 0; i < 50; i++) {
            new Thread(() -> System.out.println(stack.pop())).start();
        }
    }

}

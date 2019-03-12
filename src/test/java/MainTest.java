
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MainTest {
     MyStack<String> stack;

     @Before
    public void init() {
        stack = new MyStack<String>();
    }

    @Test
    public void mainTest() throws InterruptedException {
//        intStreamPush();
        pushTest();
        Thread.sleep(10*1000L);
        System.out.println("next " + stack.getNext());
//        intStreamPop();
        popTest();

    }

    public void intStreamPush() {
        IntStream.range(0, 50)
                .parallel()
                .forEach(i -> {
                            stack.push("line_" + i);
//                            System.out.println(i);
                        }
                );
        Assert.assertTrue(stack.getNext()==50);

    }

    public void intStreamPop() {
        Set<String> set = IntStream.range(0, 50)
                .parallel()
                .mapToObj(t -> stack.pop())
//                .peek(System.out::println)
                .collect(Collectors.toSet());
        System.out.println("set size: " + set.size());
        Assert.assertTrue(set.size() == 50);
    }

    public void pushTest() throws InterruptedException {
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
        Thread.sleep(100);
        Assert.assertTrue(stack.getNext()==50);
    }

    public void popTest() {
        System.out.println("popTest");
        for (int i = 0; i < 50; i++) {
            new Thread(() -> System.out.println(stack.pop())).start();
        }
    }

}

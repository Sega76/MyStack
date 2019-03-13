import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class MainTest {
    MyStack<String> stack;
    AtomicInteger counter;



    @Before
    public void init() {
        stack = new MyStack<>();
        counter = new AtomicInteger();
    }

    @Test
    public void mainTest() throws InterruptedException {

        int size = 50_000;

        pushTest(size);

        popTest(size);

    }

    public void pushTest(int size) throws InterruptedException {
        counter.set(0);
        System.out.println("pushTest");

        for (int i = 0; i < size; i++) {
            int finalI = i;
            Thread thread = new Thread(()->{
                String s = "line_" + finalI;
                stack.push(s);
                counter.incrementAndGet();
            });
            thread.start();
        }

        while(counter.get()!=size){ }

    }

    public void popTest(int size) throws InterruptedException {
        counter.set(0);
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());

        System.out.println("popTest");

        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                String s = stack.pop();
                if (s != null) {
                    set.add(s);
                    counter.incrementAndGet();
                }
            }).start();
        }

        while(counter.get()!=size){ }

        System.out.println("size: "+set.size());
        Assert.assertTrue(set.size() == size);

    }
}

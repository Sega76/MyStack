
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class MainTest {
    MyStack<String> stack;

    @Before
    public void init() {
        stack = new MyStack<String>();
    }

    @Test
    public void mainTest() throws InterruptedException {
        int size = 50_000;

        pushTest(size);

        Thread.sleep(1000L);

        popTest(size);

    }

    public void pushTest(int size) throws InterruptedException {
        System.out.println("pushTest");

        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(()->{
                String s = "line_" + finalI;
                    stack.push(s);
            }).start();
        }

        Thread.sleep(1000L);

        System.out.println("next " + stack.getNext());
        Assert.assertTrue(stack.getNext() == size);
    }

    public void popTest(int size) throws InterruptedException {
        Set<String> set = Collections.synchronizedSet(new HashSet<String>());

        System.out.println("popTest");

        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                String s = stack.pop();
                if (s != null) {
                    set.add(s);
                }
            }).start();
        }

        Thread.sleep(1000L);

        System.out.println("size "+set.size());
        Assert.assertTrue(set.size() == size);

    }
}

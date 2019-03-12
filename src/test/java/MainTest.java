import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class MainTest {
    MyStackV2<String> stack;
    Set<Thread> list = new HashSet<>();

    @Before
    public void init() {
        stack = new MyStackV2<>();
    }

    @Test
    public void mainTest() throws InterruptedException {
        int size = 50_000;

        pushTest(size);

        popTest(size);

    }

    public void pushTest(int size) throws InterruptedException {
        System.out.println("pushTest");

        for (int i = 0; i < size; i++) {
            int finalI = i;
            Thread thread = new Thread(()->{
                String s = "line_" + finalI;
                stack.push(s);
            });
            list.add(thread);
            thread.start();
        }

        list.stream()
                .filter(t->t.isAlive())
                .forEach(t->{
            if (t.isAlive()) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


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

        list.stream()
                .filter(t->t.isAlive())
                .forEach(t->{
                    if (t.isAlive()) {
                        try {
                            t.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

        System.out.println("size: "+set.size());
        Assert.assertTrue(set.size() == size);

    }
}

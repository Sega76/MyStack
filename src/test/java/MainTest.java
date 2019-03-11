
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MainTest {
    static MyStack<String> stack;

    static{
        stack = new MyStack<String>();
    }

    @Test
    public void mainTest() {
        intStreamPush();
        intStreamPop();

    }

    public void intStreamPush(){
        IntStream.range(0,50)
                .parallel()
                .forEach(i->stack.push("line"+i));
    }

    public void intStreamPop(){
        Set<String> set=IntStream.range(0,50)
                .parallel()
                .mapToObj(t->stack.pop())
                .peek(System.out::println)
                .collect(Collectors.toSet());
        System.out.println("set size: "+set.size());
    }
}

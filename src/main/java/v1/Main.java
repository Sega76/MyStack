package v1;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    private static MyStack<String> stack;

    static{
     stack = new MyStack<String>();
    }

    public static void main(String[] args) {

//        intStreamPush();
//       intStreamPop();

    }

    public static void intStreamPush(){
        IntStream.range(0,50)
                .parallel()
                .forEach(i->stack.push("line"+i));
    }

    public static void intStreamPop(){
        Set<String> set=IntStream.range(0,50)
                .parallel()
                .mapToObj(t->stack.pop())
                .peek(System.out::println)
        .collect(Collectors.toSet());
        System.out.println("set size: "+set.size());
    }
}

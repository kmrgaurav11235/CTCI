import java.util.function.BiFunction;
import java.util.function.Function;

/*
http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
https://www.geeksforgeeks.org/java-bifunction-interface-methods-apply-and-addthen/

- The Java Function interface (java.util.function.Function) interface is one of the most central 
    functional interfaces in Java. The Function interface represents a function (method) that 
    takes a single parameter (T) and returns a single value (R). Here is how the Function 
    interface definition looks:

    public interface Function<T,R> {
        public <R> apply(T parameter);
    }
- The Function interface, thus, takes in 2 generics namely:
    T: denotes the type of the input argument
    R: denotes the return type of the function
- The Function interface actually contains a few extra methods in addition to the methods listed above, 
    but since they all come with a default implementation, we do not have to implement these extra 
    methods. The extra methods will be explained in later.
- The only method we have to implement to implement the Function interface is the apply() method.
- We can implement the Function interface using a class or a Java lambda expression.
- For handling primitive types, there are specific Function interfaces – ToIntFunction, ToLongFunction, 
    ToDoubleFunction, LongToIntFunction, LongToDoubleFunction, IntToLongFunction etc.

- The BiFunction Interface is also a part of the java.util.function package. It takes in 3 parameters 
    namely:
    T: denotes the type of the first argument to the function
    U: denotes the type of the second argument to the function
    R: denotes the return type of the function

    public interface BiFunction<T, U, R> {
        R apply(T t, U u);
    }
*/

public class FI_02_Function {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 47;

        Function<Integer, Integer> cubeANumber = x -> x * x * x;
        System.out.println("Cubing " + num1 + ": " + cubeANumber.apply(num1));

        BiFunction<Integer, Integer, Double> divideTwoNumbers = (x, y) -> (double) x / y;
        System.out.println("Dividing " + num1 + " and " + num2 + ": " + divideTwoNumbers.apply(num1, num2));
    }
}
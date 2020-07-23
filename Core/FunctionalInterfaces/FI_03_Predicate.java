import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/*
http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
https://www.geeksforgeeks.org/functional-interfaces-java/

- The Java Predicate interface, java.util.function.Predicate, represents a simple function that 
    takes a single value as parameter, and returns true or false. 
- Predicate functional interface definition looks:

    public interface Predicate {
        boolean test(T t);
    }
- t the input argument of generic type (T).
- The Predicate interface contains more methods than the test() method, but the rest of the methods 
    are default or static methods which we don't have to implement. 
- The Predicate functional interface is a specialization of a Function that receives a generic value 
    and only returns a boolean.

- BiPredicate<T, V> operates on two objects and returns a predicate value based on that condition. 
    public interface BiPredicate<T, U> {
        boolean test(T t, U u);
    }
- T: denotes the type of the first argument
    U: denotes the type of the second argument
*/
public class FI_03_Predicate {
    public static void main(String[] args) {
        List<String> kings = Arrays.asList("Tar-Minyatur", "Ar-Pharazon", "Tar-Amandil", "Tar-Palantir", "Ar-Gimilzor"); 

        Predicate<String> loyals = name -> name.startsWith("Tar");

        System.out.println("Loyal Kings:");
        for (String king : kings) {
            if (loyals.test(king)) {
                System.out.println(king);
            }
        }

        int num1 = 5, num2 = 41;
        String str1 = "5.0", str2 = "41";

        BiPredicate<Integer, String> checkEquality = (num, str) -> str.equals(num + "");
        
        System.out.println("\nIs " + num1 + " == " + str1 + "? " + checkEquality.test(num1, str1));
        System.out.println("Is " + num2 + " == " + str2 + "? " + checkEquality.test(num2, str2));
    }
}
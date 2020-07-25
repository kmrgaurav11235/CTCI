import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/*
https://www.baeldung.com/java-8-functional-interfaces
http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html
https://www.geeksforgeeks.org/unaryoperator-interface-in-java/
https://www.geeksforgeeks.org/binaryoperator-interface-in-java/

- Operator interfaces are special cases of 'Function' that receive and return the same value type. 

- The UnaryOperator interface is a functional interface that represents an operation which takes a 
    single parameter and returns a parameter of the same type. 
    
    public interface UnaryOperator<T> extends Function<T, T> {
        ...
    }

    * T: the type of the input and output of the operator
- Since the UnaryOperator<T> extends the Function<T, T> type, it inherits the method T apply(T t).
- UnaryOperator<T> also has a static method identity. It returns a unary operator that always returns 
    its input argument.

-  The BinaryOperator interface is a functional interface that represents an operation which takes two 
    parameters and returns a single value. Both parameters and the return type must be of the same type.
- BinaryOperator interface is useful when implementing functions that sum, subtract, divide, multiply 
    etc. two elements of the same type, and returns a third element of the same type. 

    public interface BinaryOperator<T> extends BiFunction<T,T,T> {
        ...
    }

    * T: denotes the type of the two input arguments and the return value of the operation
- Since the BinaryOperator<T> extends the BiFunction<T, T, T> type, it inherits the method: 
    apply(T t, T u)
- BinaryOperator also has two static methods:
    * public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {...}
    * public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {...}
    These return the returns the lesser/greater of two elements according to the specified Comparator.
*/
public class FI_06_Operators {
    public static void main(String[] args) {
        List<String> eldarKings = List.of("Elwe", "Finwe", "Ingwe");

        UnaryOperator<String> kingNames = (name) -> "King ".concat(name);
        System.out.println("List of Kings: ");
        for (String king : eldarKings) {
            System.out.println(kingNames.apply(king));
        }

        Map<String, String> capitalCity = new HashMap<>();
        capitalCity.put("Elwe", "Doriath");
        capitalCity.put("Finwe", "Tirion");
        capitalCity.put("Ingwe", "Taniquetil");

        BinaryOperator<String> kingCities = (name, city) -> name.concat(": King of " + city);
        System.out.println("\nList of Kings and their cities:");
        for (String kingCity : capitalCity.keySet()) {
            System.out.println(kingCities.apply(kingCity, capitalCity.get(kingCity)));
        }
    }
}
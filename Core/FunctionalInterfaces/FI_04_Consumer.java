import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/*
https://www.baeldung.com/java-8-functional-interfaces
https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/
https://www.geeksforgeeks.org/java-8-biconsumer-interface-in-java-with-examples/

- Consumer represents an operation that accepts a single input argument and returns no result.
- Definition:

    public interface Consumer<T> {
        void accept(T t);
    }

- T: denotes the type of the input argument to the operation
- The lambda expression assigned to an object of Consumer type is used to define its accept() 
    method. It applies the given operation on its argument.
- The lambda passed to the List.forEach() method implements the Consumer functional interface
    as doesn't needs to return anything.

    public void forEach(Consumer<? super E> action) {
        ...
    }

- BiConsumer definition:

    public interface BiConsumer<T, U> {
        void accept(T t, U u);
    }
    * T t the first input argument
    * U u the second input argument
- One of the uses of BiConsumer is iterating through the entries of a map:
    public void forEach(BiConsumer<? super K, ? super V> action) {
        ...
    }
*/
public class FI_04_Consumer {
    public static void main(String[] args) {
        List<String> eldarKings = List.of("Elwe", "Finwe", "Ingwe");

        Consumer<String> printNames = name -> System.out.println("King " + name);
        eldarKings.forEach(printNames);

        Map<String, String> capitalCity = new HashMap<>();
        capitalCity.put("Elwe", "Doriath");
        capitalCity.put("Finwe", "Tirion");
        capitalCity.put("Ingwe", "Taniquetil");

        BiConsumer<String, String> printCapital = (king, capital) -> System.out
                .println("{ King: " + king + ", Capital: " + capital + " }");
        capitalCity.forEach(printCapital);
    }
}
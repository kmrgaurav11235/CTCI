import java.util.function.Supplier;

/*
https://www.baeldung.com/java-8-functional-interfaces

- The Supplier functional interface is yet another specialization of 'Function'. It does not take in 
    any argument but produces a value of type T.

    public interface Supplier<T> {
        T get();
    }

- It can also be thought of as a factory interface and is typically used for lazy generation of values.
*/
public class FI_05_Supplier {
    public static void main(String[] args) {
        Supplier<Double> randomDoubleGenerator = () -> Math.random();

        System.out.println("Generating 10 numbers between 0 and 1");
        for (int i = 0; i < 10; i++) {
            System.out.println(randomDoubleGenerator.get());
        }
    }
}
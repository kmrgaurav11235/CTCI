import java.util.Random;
import java.util.stream.Stream;

/* 
https://www.baeldung.com/java-8-streams

- builder(), generate() and iterate() are static methods in the Stream class used for Stream creation.
*/
public class Stream_06_BuilderGenerateIterate {
    public static void main(String[] args) {
        /*
        When builder() is used the desired type should be additionally specified in the right part of 
        the statement, otherwise the build() method will create an instance of the Stream<Object>.
        */
        Stream<String> song = Stream.<String>builder()
                                .add("Far")
                                .add("over")
                                .add("the")
                                .add("misty")
                                .add("mountains")
                                .add("cold")
                                .build();
        
        System.out.println("Song stream:");
        printStream(song);
        
        /*
        The generate() method accepts a Supplier<T> for element generation. As the resulting stream is 
        infinite, developer should specify the desired size using limit. Otherwise, the generate() 
        method will work until it reaches the memory limit.
        */
        Random random = new Random();
        Stream<Integer> randomIntegerStream = Stream.generate(() -> random.nextInt(101)).limit(10);
        System.out.println("\nRandom Integer stream:");
        printStream(randomIntegerStream);

        /*
        Another way of creating an infinite stream is by using the iterate() method:
            Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        The first element of the resulting stream is a first parameter of the iterate() method. For 
        creating every following element the specified function is applied to the previous element.
        */
        Stream<Integer> oddNumberStream = Stream.iterate(3, x -> x + 2).limit(10);
        System.out.println("\nOdd Number stream:");
        printStream(oddNumberStream);
    }

    private static void printStream(Stream<?> stream) {
        stream.forEach(word -> System.out.print(word + " "));
        System.out.println("");
    }
}
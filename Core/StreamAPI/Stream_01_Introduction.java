import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
https://www.baeldung.com/java-8-streams-introduction
http://tutorials.jenkov.com/java-functional-programming/streams.html

- The Java Stream API provides a functional approach to processing collections of objects. The 
    java.util.stream package contains classes for processing sequences of elements.
- The central API class is the Stream<T>.
- A Java Stream is a component that is capable of internal iteration of its elements, meaning it 
    can iterate its elements itself. In contrast, when you are using the Java Collections iteration 
    features (e.g a Java Iterator or the Java for-each loop used with a Java Iterable) you have to 
    implement the iteration of the elements yourself. 
- The Java Stream API is not related to the Java InputStream and Java OutputStream of Java IO. The 
    InputStream and OutputStream are related to streams of bytes. The Java Stream API is for 
    processing streams of objects - not bytes.
- The Stream interface has a selection of Terminal and Non-terminal (Intermediate) operations. 
- A Non-terminal (Intermediate) stream operation allows chaining.
- A terminal stream operation starts the internal iteration of the elements and returns a result. 
- It's also worth noting that operations on streams don't change the source.
*/
public class Stream_01_Introduction {
    public static void main(String[] args) {
        /*
        Stream Creation: 
        Streams can be created from different element sources e.g. collection or array with the help 
        of stream() and of() methods.
        */
        String [] queens = {"Melian", "Miriel Serinde", "Indis"};
        Stream<String> queensStream = Arrays.stream(queens);

        List<String> kings = List.of("Elwe", "Finwe", "Ingwe");
        Stream<String> kingsStream = kings.stream();

        Stream<String> cities = Stream.of("Utumno", "Minas Morgul", "Doriath");

        /*
        Multi-threading With Streams:
        Stream API also simplifies multi-threading by providing the parallelStream() method that runs 
        operations over stream's elements in parallel mode.
        */
        Stream<String> kingsParallelStream = kings.parallelStream();
    }
}
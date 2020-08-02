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

        Stream<String> citiesStream = Stream.of("Utumno", "Minas Morgul", "Doriath");

        /*
        Concatenate Streams:
        The Java Stream interface contains a static method called concat() which can concatenate two streams 
        into one. The result is a new Stream which contains all of the elements from the first stream, 
        followed by all of the elements from the second stream. 
        */
        Stream<String> kingsAndQueens = Stream.concat(kingsStream, queensStream);
        Stream<String> kingsQueensAndCities = Stream.concat(kingsAndQueens, citiesStream);
        System.out.println("Kings Queens and Cities:");
        printStream(kingsQueensAndCities);
    }

	private static void printStream(Stream<String> wordStream) {
		wordStream.forEach(word -> System.out.print(word + " "));
		System.out.println("");
	}
}
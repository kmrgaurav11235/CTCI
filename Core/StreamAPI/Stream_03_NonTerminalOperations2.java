import java.util.List;
import java.util.stream.Stream;

// http://tutorials.jenkov.com/java-functional-programming/streams.html

public class Stream_03_NonTerminalOperations2 {
    public static void main(String[] args) {
        List<String> jennyOfOldStones = List.of(
            "they", "danced", "through", "the", "day",
            "and", "into", "the", "night", 
            "through", "the", "snow", "that", "swept", "through", "the", "hall");
        
        /*
        distinct():
        The Java Stream distinct() method returns a new Stream which will only contain the distinct 
        elements from the original stream. Any duplicates will be eliminated.
        */
        System.out.println("Distinct words:");
        Stream<String> distinctWords = jennyOfOldStones.stream().distinct();
        printStream(distinctWords);

        /*
        limit():
        The Java Stream limit() method can limit the number of elements in a stream to a number given 
        to the limit() method as parameter. 
        */
        System.out.println("\nFirst five words:");
        Stream<String> firstFiveWords = jennyOfOldStones.stream().limit(5);
        printStream(firstFiveWords);
    }

	private static void printStream(Stream<String> wordStream) {
		wordStream.forEach(word -> System.out.print(word + " "));
		System.out.println("");
	}
}
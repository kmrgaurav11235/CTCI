import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
https://www.baeldung.com/java-8-streams-introduction
http://tutorials.jenkov.com/java-functional-programming/streams.html

- The non-terminal stream operations of the Java Stream API transform or filter the elements in the 
	stream. When you add a non-terminal operation to a stream, you get a new stream back as result. 
	The new stream represents the stream of elements resulting from the original stream with the non-
	terminal operation applied.
- It is quite common to chain the calls to non-terminal operations on a Java Stream. 
-  Many non-terminal Stream operations can take a Java Lambda Expression as parameter. For instance, 
	the Function or Predicate interface.
*/
public class Stream_02_NonTerminalOperations1 {
    public static void main(String[] args) {
		List<String> words = List.of("Thrice", "he", "was", "crushed", "to", "his", "knees", "and", 
				"thrice", "arose", "again", "and", "bore", "up", "his", "broken", "shield", "and", 
				"stricken", "helm");
		/*
		filter():
		The filter() method can be used to filter out elements from a Java Stream. It takes a Predicate 
		which is called for each element in the stream. If the element is to be included in the resulting 
		Stream, the Predicate should return true. If the element should be excluded, the Predicate should 
		return false. 
		filter() generally decreases the number of elements in the Stream.
		*/
		System.out.println("Filtering 5-character words:");
		Stream<String> fiveCharacterWords = words.stream().filter(word -> word.length() == 5);
		printStream(fiveCharacterWords);
		
		/*
		map():
		The Java Stream map() method converts (maps) an element to another object. It takes a Function
		using which it performs the mapping.
		map() keeps the number of elements in the Stream the same.
		*/
		System.out.println("\nMapping words to upper case:");
		Stream<String> upperCaseWords = words.stream().map(word -> word.toUpperCase());
		printStream(upperCaseWords);

		/*
		flatMap():
		The Java Stream flatMap() methods maps a single element into multiple elements. The idea is that you 
		"flatten" each element from a complex structure consisting of multiple internal elements, to a "flat" 
		stream consisting only of these internal elements.
		For instance, imagine you have an object with nested objects (child objects). Then you can map that 
		object into a "flat" stream consisting of itself plus its nested objects - or only the nested objects. 
		You could also map a stream of Lists of elements to the elements themselves. Or map a stream of strings 
		to a stream of words in these strings - or to the individual Character instances in these strings. 
		*/
		System.out.println("\nFlat-Mapping song to words:");
		List<String> jennyOfOldStones = List.of("High in the halls of the kings who are gone",
				"Jenny would dance with her ghosts", 
				"The ones she had lost and the ones she had found",
				"And the ones who had loved her the most");
		Stream<String> jennyOfOldStonesSongWords = jennyOfOldStones.stream().flatMap(sentence -> {
			String [] lyricsWords = sentence.split(" ");
			return Arrays.stream(lyricsWords);
		});
		printStream(jennyOfOldStonesSongWords);
	}

	private static void printStream(Stream<String> wordStream) {
		wordStream.forEach(word -> System.out.print(word + " "));
		System.out.println("");
	}
}
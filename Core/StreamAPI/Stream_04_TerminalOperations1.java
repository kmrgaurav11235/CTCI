import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
http://tutorials.jenkov.com/java-functional-programming/streams.html

- The terminal operations of the Java Stream interface typical return a single value. 
- Once the terminal operation is invoked on a Stream, the iteration of the Stream and any of the chained streams 
    will get started. Once the iteration is done, the result of the terminal operation is returned.
- A terminal operation typically does not return a new Stream instance. Thus, once you call a terminal operation 
    on a stream, the chaining of Stream instances from non-terminal operation ends.
*/
public class Stream_04_TerminalOperations1 {
    public static void main(String[] args) {
        List<String> jennyOfOldStones = List.of(
            "from", "winter", "to", "summer", "then", "winter", "again", 
            "till", "the", "walls", "did", "crumble", "and", "fall");
        
        /*
        Matching:
        * anyMatch():
            The anyMatch() method is a terminal operation that takes a single Predicate as parameter, starts the 
            internal iteration of the Stream, and applies the Predicate parameter to each element. If the 
            Predicate returns true for any of the elements, the anyMatch() method returns true. If no elements 
            match the Predicate, anyMatch() will return false.
        * allMatch():
            The allMatch() method is a terminal operation that takes a single Predicate as parameter, starts the 
            internal iteration of elements in the Stream, and applies the Predicate parameter to each element. If 
            the Predicate returns true for all elements in the Stream, the allMatch() will return true. If not all 
            elements match the Predicate, the allMatch() method returns false.
        * noneMatch():
            The noneMatch() method is a terminal operation that will iterate the elements in the stream and return 
            true or false. It will return true if no elements are matched by the Predicate, and false if one or more 
            elements are matched. 
        */
        System.out.println("Found the word \"crumble\"? " 
            + jennyOfOldStones.stream().anyMatch(word -> word.equals("crumble")));

        System.out.println("\nAll words are lower-case? " 
            + jennyOfOldStones.stream().anyMatch(word -> word.equals(word.toLowerCase())));
        
        System.out.println("\nDid not find the word \"jenny\"? " 
            + jennyOfOldStones.stream().noneMatch(word -> word.equals("jenny")));

        /*
        collect():
        The collect() method is a terminal operation that starts the internal iteration of elements, and collects 
        the elements in the stream in a collection or object of some kind.
        The collect() method takes a Collector (java.util.stream.Collector) as parameter. The Java class java.util
        .stream.Collectors contains a set of pre-implemented Collector implementations you can use, for the most 
        common operations.
        */
        List<String> wordsStartingWithT = jennyOfOldStones.stream().filter(word -> word.startsWith("t"))
                .collect(Collectors.toList());
        System.out.println("\nWords starting with \"T\": " + wordsStartingWithT);
        /*
        count():
        The count() method is a terminal operation which counts the elements.
        */
        
        long countWordsStartingWithT = jennyOfOldStones.stream().filter(word -> word.startsWith("t"))
                .count();
        System.out.println("\nNumber of words starting with \"T\": " + countWordsStartingWithT);

        /*
        Find:
        * findAny():
            The findAny() method can find a single element from the Stream. The element found can be from anywhere in 
            the Stream. There is no guarantee about from where in the stream the element is taken. It also returns an 
            Optional from which you can obtain the element, if present. 
        * findFirst():
            The findFirst() method finds the first element in the Stream, if any elements are present in the Stream. 
            It also returns an Optional from which you can obtain the element, if present. 
        */
        Optional<String> anyWord = jennyOfOldStones.stream().map(word -> word.toUpperCase()).findAny();
        Optional<String> firstWord = jennyOfOldStones.stream().map(word -> word.toUpperCase()).findFirst();

        System.out.println("\nSelecting any word in upper case: " + anyWord.get());
        System.out.println("\nSelecting first word in upper case: " + firstWord.get());
    }
}
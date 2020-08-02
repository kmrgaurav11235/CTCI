import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// http://tutorials.jenkov.com/java-functional-programming/streams.html
public class Stream_05_TerminalOperations2 {
    public static void main(String[] args) {
        List<String> nimrodelSong = List.of(
            "Where", "now", "she", "wanders", "none", "can", "tell",
            "In", "sunlight", "or", "in", "shade",
            "For", "lost", "of", "yore", "was", "Nimrodel",
            "And", "in", "the", "mountains", "strayed");
        /*
        forEach():
        The forEach() method is a terminal operation which starts the internal iteration of the elements in 
        the Stream, and applies a Consumer (java.util.function.Consumer) to each element in the Stream. The 
        forEach() method returns void.
        */
        System.out.print("Printing words that start with capital letters: ");
        nimrodelSong.stream().filter(word -> word.substring(0, 1).equals(word.substring(0, 1).toUpperCase()))
            .forEach(word -> System.out.print("\"" + word + "\" "));
        System.out.println("");
        
        /*
        Min/Max:
        * min():
            The min() method is a terminal operation that returns the smallest element in the Stream. Order 
            is determined by the Comparator implementation you pass to the min() method. It returns Optional.
        * max():
            The max() method is a terminal operation that returns the largest element in the Stream. Order is 
            determined by the Comparator implementation you pass to the max() method. It also returns Optional.
        */
        Optional<String> min = nimrodelSong.stream().min((word1, word2) -> word1.compareTo(word2));
        Optional<String> max = nimrodelSong.stream().max((word1, word2) -> word1.compareTo(word2));
        
        System.out.println("\nAlphabetically first word: " + min.get());
        System.out.println("Alphabetically last word: " + max.get());
        /*
        reduce():
        The reduce() method is a terminal operation that can reduce all elements in the stream to a single 
        element. It returns an Optional.
        */
        System.out.println("\nConcatenating all the words: ");
        Optional<String> reducedString = nimrodelSong.stream().reduce((word1, word2) -> word1.concat(" " + word2));
        System.out.println(reducedString.get());

        /*
        toArray():
        The toArray() method is a terminal operation that starts the internal iteration of the elements in the 
        stream, and returns an array of Object (not the Stream type) containing all the elements.
        */
        Object[] array = nimrodelSong.stream().filter(word -> word.startsWith("s")).toArray();
        System.out.println("\nWords that start with \"s\": " + Arrays.toString(array));
    }
}
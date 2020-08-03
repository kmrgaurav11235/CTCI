import java.util.List;
import java.util.stream.Stream;

/*
https://www.baeldung.com/java-8-streams

- It is possible to instantiate a stream and to have an accessible reference to it as long as only 
    intermediate operations were called. Executing a terminal operation makes a stream inaccessible.
- A stream by itself is worthless, the real thing a user is interested in is a result of the terminal 
    operation, which can be a value of some type or an action applied to every element of the stream. 
    Only one terminal operation can be used per stream.
- An attempt to reuse the same reference after calling the terminal operation will trigger the 
    IllegalStateException.
- As the IllegalStateException is a RuntimeException, a compiler will not signalize about a problem. 
    So, it is very important to remember that Java 8 streams can't be reused.
*/
public class Stream_07_ReferencingAStream {
    public static void main(String[] args) {
        List<String> song = List.of(    
            "The", "elven", "ship", "in", "haven", "grey",
            "Beneath", "the", "mountain", "lee",
            "Awaited", "her", "for", "many", "a", "day", 
            "Beside", "the", "roaring", "sea");
        
        Stream<String> songStream = song.stream();
        
        // Terminal operation
        long countWords =  songStream.count();
        System.out.println("Number of words in song: " + countWords);

        // Referencing stream after terminal operation: IllegalStateException
        try {
            System.out.println(songStream.findAny());
        } catch (IllegalStateException e) {
            System.err.println("IllegalStateException while accessing stream: " + e.getMessage());
        }
    }
}
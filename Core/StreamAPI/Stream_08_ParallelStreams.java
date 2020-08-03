import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
https://www.baeldung.com/java-8-streams

- The Streams API allows creating parallel streams, which perform operations in a parallel mode. 
- When the source of a stream is a Collection or an array it can be achieved with the help of the 
    parallelStream() method.
- If the source of stream is something different than a Collection or an array, the parallel() 
    method should be used.
- Under the hood, Stream API automatically uses the 'ForkJoin' framework to execute operations in 
    parallel. By default, the common thread pool will be used and there is no way (at least for now) 
    to assign some custom thread pool to it.
- When using streams in parallel mode, avoid blocking operations and use parallel mode when tasks need 
    the similar amount of time to execute (if one task lasts much longer than the other, it can slow down 
    the complete app’s workflow).
*/
public class Stream_08_ParallelStreams {
    public static void main(String[] args) {
        List<String> song = List.of(
            "Amroth", "beheld", "the", "fading", "shore", 
            "Now", "long", "beyond", "the", "swell",
            "And", "cursed", "the", "faithless", "ship", "that", "bore",
            "Him", "far", "from", "Nimrodel");
        
        Stream<String> parallelSongStream = song.parallelStream();
        System.out.println("Is this a parallel stream? " + parallelSongStream.isParallel());

        System.out.println("Words that are even-character long in lower case:");
        parallelSongStream.map(word -> word.toLowerCase()).filter(word -> (word.length() % 2 == 0))
                .forEach(word -> System.out.print(word + " "));

        IntStream parallelIntStream = IntStream.range(1, 101).limit(10).parallel();
        System.out.println("\n\nIs this a parallel stream? " + parallelIntStream.isParallel());

        System.out.println("Numbers that are divisible by 4:");
        parallelIntStream.filter(num -> num % 4 == 0).forEach(num -> System.out.print(num + " "));
        System.out.println("");
    }
}
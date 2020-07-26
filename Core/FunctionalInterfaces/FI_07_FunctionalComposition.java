import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/*
http://tutorials.jenkov.com/java-functional-programming/functional-composition.html

- Functional composition is a technique to combine multiple functions into a single function which uses 
    the combined functions internally. 
*/
public class FI_07_FunctionalComposition {

    static void functionComposition() {
        int num = 3;

        Function<Integer, Integer> doubleAnInteger = x -> 2 * x;
        Function<Integer, Integer> squareAnInteger = x -> x * x;

        /*
        compose():
        The Java Function compose() method composes a new Function instance from the Function instance it 
        is called on, and the Function instance passed as parameter to the compose() method.
        The Function returned by compose() will first call the Function passed as parameter to compose(), 
        and then it will call the Function which compose() was called on. 
        */
        Function<Integer, Integer> doubleTheSquare = doubleAnInteger.compose(squareAnInteger);
        System.out.println("Double the square of " + num + ": " + doubleTheSquare.apply(num));

        /*
        andThen():
        The Java Function andThen() method works opposite of the compose() method. A Function composed with 
        andThen() will first call the Function that andThen() was called on, and then it will call the 
        Function passed as parameter to the andThen() method. 
        */
        Function<Integer, Integer> squareTheDouble = doubleAnInteger.andThen(squareAnInteger);
        System.out.println("Square the double of " + num + ": " + squareTheDouble.apply(num));

        /*
        Consumer: Has andThen() but not compose().
        Supplier: Has neither.
        */
    }

    static void predicateComposition() {
        List<String> citiesList = List.of("Utumno", "Minas Morgul", "Doriath", "Gondor", "Minas Tirith", "Angband",
                "Nargothrond", "Gondolin");

        Predicate<String> citiesStartingWithM = city -> city.startsWith("M");
        Predicate<String> citiesWithA = city -> city.contains("a");
        /*
        Since Predicates are used to return boolean, the following methods have been created for it:
        - Predicate<T> and(Predicate<? super T> other)
        - Predicate<T> or(Predicate<? super T> other)
        - Predicate<T> not(Predicate<? super T> target)
        */

        // Not
        Predicate<String> citiesWithoutA = Predicate.not(citiesWithA);
        System.out.println("Cities without \"a\" in their names: ");
        for (String city : citiesList) {
            if (citiesWithoutA.test(city)) {
                System.out.println(city);
            }
        }

        // Or
        Predicate<String> citiesStartingWithMOrWithAInThem = citiesStartingWithM.or(citiesWithA);
        System.out.println("\nCities starting with \"M\" or with \"a\" in their names: ");
        for (String city : citiesList) {
            if (citiesStartingWithMOrWithAInThem.test(city)) {
                System.out.println(city);
            }
        }

        // And
        Predicate<String> citiesStartingWithMAndWithAInThem = citiesStartingWithM.and(citiesWithA);
        System.out.println("\nCities starting with \"M\" and with \"a\" in their names: ");
        for (String city : citiesList) {
            if (citiesStartingWithMAndWithAInThem.test(city)) {
                System.out.println(city);
            }
        }
    }
    public static void main(String[] args) {
        functionComposition();
        predicateComposition();
    }
}
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://www.journaldev.com/1663/java-generics-example-method-class-interface
https://www.baeldung.com/java-generics

- Sometimes we don’t want the whole class to be parameterized, in that case, we can create java generics 
    method. 
- Generic methods are those methods that are written with a single method declaration and can be called with 
    arguments of different types. The compiler will ensure the correctness of whichever type is used. 
- These are some properties of generic methods:
    * Generic methods have a type parameter (the diamond operator enclosing the type) before the return 
        type of the method declaration.
    * Type parameters can be bounded.
    * Generic methods can have different type parameters separated by commas in the method signature.
    * Method body for a generic method is just like a normal method.

- Since the constructor is a special kind of method, we can use generics type in constructors too.
*/
public class Generics_04_GenericMethod {
    //Java Generic Method
    public <T> List<T> getListFromArray(T [] arr) {
        return Arrays.stream(arr).collect(Collectors.toList());
    } 
    // the <T> in the method signature implies that the method will be dealing with generic type T. This is 
    // needed even if the method is returning void.

    // Methods can deal with more than one generic type. In this case, all generic types must be added to the 
    // method signature.
    public <T, U> List<U> getListOfNewTypeFromArray(T[] arr, Function<T, U> mapperFunction) {
        return Arrays.stream(arr).map(mapperFunction).collect(Collectors.toList());
    }
	public static void main(String args[]){
        Generics_04_GenericMethod g = new Generics_04_GenericMethod();

        Integer [] intArray = {5, 6, 9, 10};
        String [] strArray = {"Hut-hut one", "hut-hut two"};

        System.out.println("Integer List: " + g.getListFromArray(intArray));
        System.out.println("String List: " + g.getListFromArray(strArray));

        List<String> convertedList = g.getListOfNewTypeFromArray(intArray, Object::toString);
        System.out.println("Converting Integer List to String List: " + convertedList);
	}
}
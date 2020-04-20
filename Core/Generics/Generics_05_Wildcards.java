import java.util.List;

/*
https://www.geeksforgeeks.org/wildcards-in-java/
https://www.journaldev.com/1663/java-generics-example-method-class-interface#java-generics-wildcards
https://www.baeldung.com/java-generics

- The question mark (?) is known as the wildcard in generic programming. It represents an unknown type. 
- The wildcard can be used in a variety of situations such as the type of a parameter, field, local 
    variable or return type. Unlike arrays, different instantiations of a generic type are not compatible 
    with each other, not even explicitly.
- It is known that Object is the supertype of all Java classes, however, a collection of Object is not 
    the supertype of any collection.
- For example, a List<Object> is not the supertype of List<String> and assigning a variable of type 
    List<Object> to a variable of type List<String> will cause a compiler error. This is to prevent 
    possible conflicts that can happen if we add heterogeneous types to the same collection.
- The Same rule applies to any collection of a type and its subtypes. Consider this example:

    {code}	
    public static void paintAllBuildings(List<Building> buildings) {
        buildings.forEach(Building::paint);
    }
    {/code}

- If we imagine a subtype of Building, e.g. a House, we can't use this method with a list of House, even 
    though House is a subtype of Building. If we need to use this method with type Building and all its 
    subtypes, then the bounded wildcard can do the magic:

	{code}
    public static void paintAllBuildings(List<? extends Building> buildings) {
        ...
    }
    {/code}
- Now, this method will work with type Building and all its subtypes. This is called an upper bounded wildcard 
    where type Building is the upper bound.

- Types of wildcards in Java:
    1. Upper Bounded Wildcards
    2. Lower Bounded Wildcards
    3. Unbounded Wildcards
*/
public class Generics_05_Wildcards {
    /*
    Upper Bounded Wildcards: 
    - These wildcards can be used when you want to relax the restrictions on a variable. 
    - For example, say you want to write a method that works on List < integer >, List < double >, and 
        List < number > , you can do this  using an upper bounded wildcard.
    - To declare an upper-bounded wildcard, use the wildcard character (‘?’), followed by the extends keyword, 
        followed by its upper bound.
    */
    public double getDoubleSum (List<? extends Number> numList) {
        double sum = 0;
        for (Number num : numList) {
            sum = sum + num.doubleValue();
        }
        return sum;
    }
    /*
    Lower Bounded Wildcards:
    - It is expressed using the wildcard character ('?'), followed by the super keyword, followed by its 
        lower bound: <? super A>.
    */
    public void printOnlyIntegers(List<? super Integer> intList) {
        StringBuilder sb = new StringBuilder("[");
        for (Object object : intList) {
            sb.append(object + " ");
        }
        sb.append("]");
        System.out.println(sb);
    }
    /*
    - Here arguments can be Integer or superclass of Integer(which is Number). The method printOnlyIntegers() 
        will only take Integer or its superclass objects. However if we pass list of type Double then we will 
        get compilation error. It is because only the Integer field or its superclass can be passed. Double is 
        not the superclass of Integer.
    - Use extend wildcard when you want to get values out of a structure and super wildcard when you put 
        values in a structure. Don’t use wildcard when you get and put values in a structure.
    - You can specify an upper bound for a wildcard, or you can specify a lower bound, but you cannot specify 
        both.
    */

    /*
    Unbounded Wildcard: 
    - This wildcard type is specified using the wildcard character (?), for example, List<?>. This is a list of 
        unknown type. 
    - These are useful in the following cases:
        * When writing a method which can be employed using functionality provided in Object class.
        * When the code is using methods in the generic class that don’t depend on the type parameter.

    */
    public void print(List<?> list) {
        StringBuilder sb = new StringBuilder("[");
        for (Object object : list) {
            sb.append(object);
        }
        sb.append("]");
        System.out.println(sb);
    }
    public static void main(String[] args) {
        Generics_05_Wildcards g = new Generics_05_Wildcards();

        List<Double> doubleList = List.of(0.7, 1.1, 2.9);
        List<Integer> intList = List.of(2, 6, 9, 11);

        System.out.println("Sum (doubleList): " + g.getDoubleSum(doubleList));
        System.out.println("Sum (intList): " + g.getDoubleSum(intList));

        g.printOnlyIntegers(intList);
        // Below line will give compilation error
        // g.printOnlyIntegers(doubleList);
    }
}
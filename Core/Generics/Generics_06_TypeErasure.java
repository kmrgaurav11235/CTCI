/*
https://www.baeldung.com/java-generics

- Generics were added to Java to ensure type safety and to ensure that they wouldn't cause overhead 
    at runtime. So, the compiler applies a process called type erasure on generics at compile time.
- Type erasure removes all type parameters and replaces it with their bounds or with Object if the 
    type parameter is unbounded. Thus the bytecode after compilation contains only normal classes, 
    interfaces and methods thus ensuring that no new types are produced. Proper casting is applied as 
    well to the Object type at compile time.

- This is an example of type erasure:
    {code}
    public <T> List<T> genericMethod(List<T> list) {
        return list.stream().collect(Collectors.toList());
    }
    {/code}

- With type erasure, the unbounded type T is replaced with Object as follows:
	{code}
    // for illustration
    public List<Object> withErasure(List<Object> list) {
        return list.stream().collect(Collectors.toList());
    }
    
    // which in practice results in
    public List withErasure(List list) {
        return list.stream().collect(Collectors.toList());
    }
    {/code}

- If the type is bounded, then the type will be replaced by the bound at compile time:
    {code}
    public <T extends Building> void genericMethod(T t) {
        ...
    }
    {/code}
- would change after compilation:
    {code}
    public void genericMethod(Building t) {
        ...
    }
    {/code}
- Generics and Primitive Data Types - A restriction of generics in Java is that the type parameter cannot 
    be a primitive type. For example, the following doesn't compile:
    {code}
    List<int> list = new ArrayList<>();
    list.add(17);
    {/code}

- To understand why primitive data types don't work, let's remember that generics are a compile-time feature, 
    meaning the type parameter is erased and all generic types are implemented as type Object.
- So, type parameters must be convertible to Object. Since primitive types don't extend Object, we can't use 
    them as type parameters.
- Future versions of Java might allow primitive data types for generics. 
    Project Valhalla (https://openjdk.java.net/projects/valhalla/) aims at improving the way generics are 
    handled. The idea is to implement generics specialization as described in JEP 218 
    (https://openjdk.java.net/jeps/218).
*/
class Generics_06_TypeErasure {
    public static void main(String[] args) {
    }
}
/*
https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
Singleton Pattern
    - Singleton pattern restricts the instantiation of a class and ensures that only one instance of the class 
        exists in the java virtual machine.
    - The singleton class must provide a global access point to get the instance of the class.
    - Singleton pattern is used for logging, drivers objects, caching and thread pool.
    - Singleton design pattern is also used in other design patterns like Abstract Factory, Builder, Prototype, Facade etc.
    - Singleton design pattern is used in core java classes also, for example java.lang.Runtime, java.awt.Desktop.

Java Singleton Pattern Implementation:
To implement a Singleton pattern, we have different approaches but all of them have the following common concepts.
    - Private constructor to restrict instantiation of the class from other classes.
    - Private static variable of the same class that is the only instance of the class.
    - Public static method that returns the instance of the class, this is the global access point for outer world to get 
        the instance of the singleton class.
*/
/*
Eager initialization
- In eager initialization, the instance of Singleton Class is created at the time of class loading, this is the easiest method 
    to create a singleton class but it has a drawback that instance is created even though client application might not be using it.
- If your singleton class is not using a lot of resources, this is the approach to use. But in most of the scenarios, Singleton 
    classes are created for resources such as File System, Database connections, etc. We should avoid the instantiation until unless 
    client calls the getInstance method. Also, this method doesn’t provide any options for exception handling.
*/
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerSingleton(){
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

/*
Static block initialization
- Static block initialization implementation is similar to eager initialization, except that instance of class is created in the static 
    block that provides option for exception handling.
- Both eager initialization and static block initialization creates the instance even before it’s being used and that is not the best 
    practice to use. So in further sections, we will learn how to create a Singleton class that supports lazy initialization.
*/
class StaticBlockSingleton {
    private static final StaticBlockSingleton INSTANCE;

    //private constructor to avoid client applications to use constructor
    private StaticBlockSingleton(){
    }

    static {
        INSTANCE = new StaticBlockSingleton();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }
}

/*
Lazy Initialization
- Lazy initialization method to implement Singleton pattern creates the instance in the global access method.
- The implementation works fine in case of the single-threaded environment but when it comes to multi-threaded systems, it can cause 
    issues if multiple threads are inside the if-condition at the same time. It will destroy the singleton pattern and both threads will 
    get the different instances of the singleton class.
*/
class LazySingleton {
    private static LazySingleton INSTANCE; // Can't use final because of the if-test

    //private constructor to avoid client applications to use constructor
    private LazySingleton(){
    }

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }

}

class Singleton_01_EagerStaticAndLazy {
    public static void main(String[] args) {
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
        StaticBlockSingleton staticBlockSingleton = StaticBlockSingleton.getInstance();
        LazySingleton lazySingleton = LazySingleton.getInstance();
    }
}
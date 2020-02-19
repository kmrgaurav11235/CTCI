/*
Thread Safe Singleton
- The easier way to create a thread-safe singleton class is to make the global access method synchronized, so that 
    only one thread can execute this method at a time. This works fine and provides thread-safety but it reduces the 
    performance because of the cost associated with the synchronized method, although we need it only for the first 
    few threads who might create the separate instances.
- To avoid this extra overhead every time, double checked locking principle is used. In this approach, the synchronized 
    block is used inside the if-condition with an additional check to ensure that only one instance of a singleton class 
    is created.
*/
class DoubleCheckedSingleTon {
    private static volatile DoubleCheckedSingleTon instance;

    private DoubleCheckedSingleTon() {
    }

    public static DoubleCheckedSingleTon getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleTon.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleTon();
                }
            }
        }

        return instance;
    }
}
/*
Inner Static Class Singleton / Bill Pugh Singleton Implementation
- Prior to Java 5, java memory model had a lot of issues and the above approaches used to fail in certain scenarios where too 
    many threads try to get the instance of the Singleton class simultaneously. So Bill Pugh came up with a different approach 
    to create the Singleton class using an inner static helper class.
- The private inner static class contains the instance of the singleton class. When the singleton class is loaded, InnerStaticClass 
    class is not loaded into memory and only when someone calls the getInstance() method, this class gets loaded and creates the 
    Singleton class instance.
- This is the most widely used approach for Singleton class as it doesn’t require synchronization. 
*/
class InnerStaticClassSingleton {

    private InnerStaticClassSingleton() {
    }

    private static class InnerStaticClass {
        private static final InnerStaticClassSingleton INSTANCE = new InnerStaticClassSingleton();
    }

    public static InnerStaticClassSingleton getInstance() {
        return InnerStaticClass.INSTANCE;
    }
}
class Singleton_02_ThreadSafe {
    public static void main(String[] args) {
        DoubleCheckedSingleTon doubleCheckedSingleTon = DoubleCheckedSingleTon.getInstance();
        InnerStaticClassSingleton innerStaticClassSingleton = InnerStaticClassSingleton.getInstance();
    }
}
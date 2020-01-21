/*
https://www.geeksforgeeks.org/synchronized-in-java/
- When multiple threads try to access the same resources it can produce erroneous and unforeseen results.
- Java provides a way of creating threads and synchronizing their task by using synchronized blocks. 
    Synchronized blocks in Java are marked with the synchronized keyword. A synchronized block in Java is 
    synchronized on some object. All synchronized blocks synchronized on the same object can only have one 
    thread executing inside them at a time. All other threads attempting to enter the synchronized block 
    are blocked until the thread inside the synchronized block exits the block.
- This synchronization is implemented in Java with a concept called monitors. Only one thread can own a monitor 
    at a given time. When a thread acquires a lock, it is said to have entered the monitor. All other threads 
    attempting to enter the locked monitor will be suspended until the first thread exits the monitor.

https://www.geeksforgeeks.org/method-block-synchronization-java/

1. Method Synchronization: It is not possible for two invocations for synchronized methods to interleave. If 
    one thread is executing the synchronized method, all others thread that invoke any synchronized method on the 
    same Object will have to wait until first thread is done with the Object.
        - When one thread is executing a synchronized method for an object, all other threads that invoke synchronized
        methods for the same object suspend execution until the first thread is done with the object.
        - To be clear, if a class has 3 synchronized methods, then only one of them can run at a time on a single object.
        - When a synchronized method exits, it automatically establishes a happens-before relationship with any 
        subsequent invocation of a synchronized method for the same object. This guarantees that changes to the 
        state of the object are visible to all threads.
        - Constructors cannot be synchronized — using the synchronized keyword with a constructor is a syntax error. 
        Synchronizing constructors doesn't make sense, because only the thread that creates an object should have 
        access to it while it is being constructed.
2. Block Synchronization: If we only need to execute some subsequent lines of code not all lines (instructions)
     of code within a method, then we should synchronize only block of the code within which required instructions
      exists. For example, lets suppose there is a method that contains 100 lines of code but there are only 10 
      lines (one after one) of code which contain critical section of code i.e. these lines can modify (change) 
      the Object’s state. So we only need to synchronize these 10 lines of code method to avoid any modification 
      in state of the Object and to ensure that other threads can execute rest of the lines within the same method 
      without any interruption.
      - Local variables are stored in Thread Stack and each thread creates its own copy of local variable. Each 
      copy is an object that has its own monitor (lock). So, if we use local variable for Block Synchronization, each
      thread will get lock for its copy and continue executing. So, we should use instance variables.
      - The only exception to above is a String local variable. As String objects are re-used within JVM (JVM has String
      pool), this will work, but better avoid it.
3. Synchronized Static Methods: Static methods are synchronized just like instance methods:
        public static synchronized void syncStaticCalculate() {
            staticSum = staticSum + 1;
        }

    These methods are synchronized on the Class object associated with the class and since only one Class object 
        exists per JVM per class, only one thread can execute inside a static synchronized method per class, 
        irrespective of the number of instances it has.

Reentrant Synchronization: A thread cannot acquire a lock owned by another thread. But a thread can acquire a lock 
    that it already owns. Allowing a thread to acquire the same lock more than once enables reentrant synchronization. 
    This describes a situation where synchronized code, directly or indirectly, invokes a method that also contains 
    synchronized code, and both sets of code use the same lock. Without reentrant synchronization, synchronized code 
    would have to take many additional precautions to avoid having a thread cause itself to block.

Important points:
    1. When a thread enters into synchronized method or block, it acquires lock and once it completes its task and 
        exits from the synchronized method, it releases the lock.
    2. When thread enters into synchronized instance method or block, it acquires Object level lock and when it enters 
        into synchronized static method or block it acquires class level lock.
    3. Java synchronization will throw null pointer exception if Object used in synchronized block is null. For example, 
        If in synchronized(instance) , instance is null then it will throw null pointer exception.
    4. In Java, wait(), notify() and notifyAll() are the important methods that are used in synchronization.
    5. You can not apply java synchronized keyword with the variables.
    6. Don’t synchronize on the non-final field on synchronized block because the reference to the non-final field may 
        change anytime and then different threads might synchronize on different objects i.e. no synchronization at all.

Advantages
    1. Multi-threading: Since java is multi-threaded language, synchronization is a good way to achieve mutual 
        exclusion on shared resource(s).
    2. Instance and Static Methods: Both synchronized instance methods and synchronized static methods can 
        be executed concurrently because they are used to lock different Objects.

Limitations
    1. Concurrency Limitations: Java synchronization does not allow concurrent reads.
    2. Decreases Efficiency: Java synchronized method run very slowly and can degrade the performance, so you 
        should synchronize the method when it is absolutely necessary otherwise not and to synchronize block 
        only for critical section of the code.

*/
class Concurrency_06_Synchronized {
    public static void main(String[] args) {
        SyncCountDown syncCountDown = new SyncCountDown();

        SyncCountDownThread t1 = new SyncCountDownThread(syncCountDown);
        t1.setName("Thread 1");
        SyncCountDownThread t2 = new SyncCountDownThread(syncCountDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }

}
class SyncCountDownThread extends Thread {
    private SyncCountDown syncCountDown;

    SyncCountDownThread(SyncCountDown syncCountDown) {
        this.syncCountDown = syncCountDown;
    }
    @Override
    public void run() {
        syncCountDown.doCountDown();
    }
}

class SyncCountDown {
    int i;
    // Method Synchronization
    // public synchronized void doCountDown() {
    public void doCountDown() {
        String color;

        switch (Thread.currentThread().getName()) {
        case "Thread 1":
            color = ThreadColor.ANSI_CYAN;
            break;
        case "Thread 2":
            color = ThreadColor.ANSI_PURPLE;
            break;
        default:
            color = ThreadColor.ANSI_WHITE;
            break;
        }
        // Block Synchronization
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}
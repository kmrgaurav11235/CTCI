import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
https://www.youtube.com/watch?v=WH5UvQJizH0&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=2
https://www.baeldung.com/java-atomic-variables

- Let's have a look at the class:
    public class Counter {
        int counter; 
    
        public void increment() {
            counter++;
        }
    }
- In the case of a single threaded environment, this works perfectly; however, as soon as we allow more than one 
    thread to write, we start getting inconsistent results.
- This is because of the simple increment operation (counter++), which may look like an atomic operation, but in 
    fact is a combination of three operations: obtaining the value, incrementing, and writing the updated value 
    back. If two threads try to get and update the value at the same time, it may result in lost updates.
- Synchronization Problem:
--------------------------------------------------
    Thread 1            |   Thread 2
--------------------------------------------------
Read Value = 1          |
                        |   Read Value = 1
                        |   Add 1 and write (= 2)
Add 1 and write (= 2)   |
--------------------------------------------------

- One of the ways to manage access to an object is to use locks. This can be achieved by using the synchronized keyword 
    in the increment method signature. The synchronized keyword ensures that only one thread can enter the method at one 
    time:
public class SafeCounterWithLock {
    private volatile int counter;
  
    public synchronized void increment() {
        counter++;
    }
}
- Additionally, we need to add the volatile keyword to ensure proper reference visibility among threads.
- Using locks solves the problem. However, performance takes a hit. When multiple threads attempt to acquire a lock, one of 
    them wins, while the rest of the threads are either blocked or suspended. The process of suspending and then resuming a 
    thread is very expensive and affects the overall efficiency of the system. In a small program, such as the counter, the 
    time spent in context switching may become much more than actual code execution, thus greatly reducing overall efficiency.

Atomic Operations
- There is a branch of research focused on creating non-blocking algorithms for concurrent environments. These algorithms 
    exploit low-level atomic machine instructions such as compare-and-swap (CAS), to ensure data integrity.
- A typical CAS operation works on three operands:
    1. The memory location on which to operate (M)
    2. The existing expected value (A) of the variable
    3. The new value (B) which needs to be set
- The CAS operation updates atomically the value in M to B, but only if the existing value in M matches A, otherwise no action 
    is taken.
- In both cases, the existing value in M is returned. This combines three steps – getting the value, comparing the value and 
    updating the value – into a single machine level operation.
- When multiple threads attempt to update the same value through CAS, one of them wins and updates the value. However, unlike 
    in the case of locks, no other thread gets suspended; instead, they're simply informed that they did not manage to update 
    the value. The threads can then proceed to do further work and context switches are completely avoided.
- One other consequence is that the core program logic becomes more complex. This is because we have to handle the scenario when 
    the CAS operation didn't succeed. We can retry it again and again till it succeeds, or we can do nothing and move on depending 
    on the use case.

Atomic Variables in Java
- The most commonly used atomic variable classes in Java are AtomicInteger, AtomicLong, AtomicBoolean, and AtomicReference. 
    These classes represent an int, long, boolean and object reference respectively which can be atomically updated. The 
    main methods exposed by these classes are:
    1. get() – gets the value from the memory, so that changes made by other threads are visible; equivalent to reading 
        a volatile variable
    2. set() – writes the value to memory, so that the change is visible to other threads; equivalent to writing a volatile 
        variable
    3. lazySet() – eventually writes the value to memory, may be reordered with subsequent relevant memory operations. One 
        use case is nullifying references, for the sake of garbage collection, which is never going to be accessed again. In 
        this case, better performance is achieved by delaying the null volatile write
    4. compareAndSet() – same as described above, returns true when it succeeds, else false.
    5. weakCompareAndSet() – same as described above, but weaker in the sense, that it does not create happens-before orderings. 
        This means that it may not necessarily see updates made to other variables.

A thread safe counter implemented with AtomicInteger is shown in the example below:
*/
class AtomicManipulator implements Runnable {
    private AtomicInteger atomicInteger;
    private String color;

    AtomicManipulator(AtomicInteger atomicInteger, String color) {
        this.atomicInteger = atomicInteger;
        this.color = color;
    }

    private void manipulate() {

        int oldValue = atomicInteger.get();
        int newValue = oldValue * 10;
        if (atomicInteger.compareAndSet(oldValue, newValue)) {
            System.out.println(color + "Thread " + Thread.currentThread().getName() + ": Old Value: " + oldValue 
                + ", New Value: " + newValue + " - SUCCESS.");
        } else {
            System.out.println(color + "Thread " + Thread.currentThread().getName() + ": Old Value: " + oldValue 
                + ", New Value: " + newValue + " - FAILURE.");
        }
    }

    @Override
    public void run() {
        while (atomicInteger.get() > 0) {
            manipulate();
        }
    }
}
class Concurrency_15_AtomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        AtomicManipulator atomicManipulator1 = new AtomicManipulator(atomicInteger, ThreadColor.ANSI_GREEN);
        AtomicManipulator atomicManipulator2 = new AtomicManipulator(atomicInteger, ThreadColor.ANSI_YELLOW);
        AtomicManipulator atomicManipulator3 = new AtomicManipulator(atomicInteger, ThreadColor.ANSI_PURPLE);

        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(atomicManipulator1);
        pool.submit(atomicManipulator2);
        pool.submit(atomicManipulator3);

        pool.shutdown();
    }
}
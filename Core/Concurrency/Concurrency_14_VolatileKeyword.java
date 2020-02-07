import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
https://www.geeksforgeeks.org/volatile-keyword-in-java/
https://www.youtube.com/watch?v=WH5UvQJizH0&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=2

- Using volatile is yet another way (like synchronized, atomic wrapper) of making class thread safe. Thread safe 
    means that a method or class instance can be used by multiple threads at the same time without any problem.
- Consider below simple example.
    class SharedObj
    {
    // Changes made to sharedVar in one thread
    // may not immediately reflect in other thread
    static int sharedVar = 6;
    }

- Suppose that two threads are working on SharedObj. If two threads run on different processors each thread may have 
    its own local copy of sharedVariable. If one thread modifies its value the change might not reflect in the original 
    one in the main memory instantly. This depends on the write policy of cache. Now the other thread is not aware of 
    the modified value which leads to data inconsistency. This is called "Visibility Problem".
- Note that write of normal variables without any synchronization actions, might not be visible to any reading thread 
    (this behavior is called sequential consistency). Although most modern hardware provide good cache coherence therefore 
    most probably the changes in one cache are reflected in other but it’s not a good practice to rely on hardware for 
    to ‘fix’ a faulty application.

    class SharedObj
    {
    // volatile keyword here makes sure that
    // the changes made in one thread are 
    // immediately reflect in other thread
    static volatile int sharedVar = 6;
    }

- Note that volatile should not be confused with static modifier. static variables are class members that are shared among 
    all objects. There is only one copy of them in main memory.

- Volatile vs synchronized:
    Before we move on let’s take a look at two important features of locks and synchronization.
        1. Mutual Exclusion: It means that only one thread or process can execute a block of code (critical section) at a time.
        2. Visibility: It means that changes made by one thread to shared data are visible to other threads.

- Java’s synchronized keyword guarantees both mutual exclusion and visibility. If we make the blocks of threads that modifies 
    the value of shared variable synchronized only one thread can enter the block and changes made by it will be reflected 
    in the main memory. All other thread trying to enter the block at the same time will be blocked and put to sleep.
- But, in some cases we may only desire the visibility and not atomicity. Use of synchronized in such situation is an overkill 
    and may cause scalability problems. Here volatile comes to the rescue. Volatile variables have the visibility features of 
    synchronized but not the atomicity features. The values of volatile variable will never be cached and all writes and reads 
    will be done to and from the main memory. However, use of volatile is limited to very restricted set of cases as most of the 
    times atomicity is desired. 
- For example a simple increment statement such as x = x + 1; or x++ seems to be a single operation but is really a compound 
    read-modify-write sequence of operations that must execute atomically.
*/
class Concurrency_14_VolatileKeyword {

    // If this is not volatile, ComplexTaskExecutor might keep running
    private volatile boolean keepRunning = true;

    class ComplexTaskExecutor implements Runnable {
        @Override
        public void run() {
            System.out.println("ComplexTaskExecutor: Starting task.");
            while (keepRunning) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ComplexTaskExecutor: Still executing.");
            }
            System.out.println("ComplexTaskExecutor: \'keepRunning\' is now false. Exiting.");
        }
    }

    class Interrupter implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Interrupter: 10 seconds are over. Stopping ComplexTaskExecutor");
            keepRunning = false;
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Concurrency_14_VolatileKeyword obj = new Concurrency_14_VolatileKeyword();
        pool.submit(obj.new ComplexTaskExecutor());
        pool.submit(obj.new Interrupter());

        pool.shutdown();
    }

}
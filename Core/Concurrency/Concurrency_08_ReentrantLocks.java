import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/*
https://www.geeksforgeeks.org/reentrant-lock-java/
https://www.geeksforgeeks.org/lock-framework-vs-thread-synchronization-in-java/
The traditional way to achieve thread synchronization in Java is by the use of "synchronized" keyword. 
While it provides a certain basic synchronization, the synchronized keyword is quite rigid in its use. 
For example, 
    - A thread can take a lock only once. 
    - The synchronized block must be within the same method, i.e. we cannot start the block in one method
    and end it in another.
    - Synchronized blocks don’t offer any mechanism of a waiting queue and after the exit of one thread, 
    any thread can take the lock. This could lead to starvation of resources for some other thread for a 
    very long period of time.
    - We cannot find out any information about an object's intrinsic lock, e.g. whether it is available.

Reentrant Locks are provided in Java to provide synchronization with greater flexibility.

What are Reentrant Locks?
- The ReentrantLock class implements the "Lock" interface and provides synchronization to methods while 
    accessing shared resources. The code which manipulates the shared resource is surrounded by calls to 
    lock and unlock method. This gives a lock to the current working thread and blocks all other threads 
    which are trying to take a lock on the shared resource.

- As the name says, ReentrantLock allow threads to enter into lock on a resource more than once. When the 
    thread first enters into lock, a hold count is set to one. Before unlocking the thread can re-enter into 
    lock again and every time hold count is incremented by one. For every unlock request, hold count is 
    decremented by one and when hold count is 0, the resource is unlocked.

- Reentrant Locks also offer a fairness parameter, by which the lock would abide by the order of the lock 
    request i.e. after a thread unlocks the resource, the lock would go to the thread which has been waiting 
    for the longest time. This fairness mode is set up by passing true to the constructor of the lock. 

- The unlock statement is always called in the finally block to ensure that the lock is released even if an 
    exception is thrown in the method body(try block).

- ReentrantLock Methods

    - lock(): Call to the lock() method increments the hold count by 1 and gives the lock to the thread if the 
        shared resource is initially free.
    - unlock(): Call to the unlock() method decrements the hold count by 1. When this count reaches zero, the 
        resource is released.
    - tryLock(): If the resource is not held by any other thread, then call to tryLock() returns true and the 
        hold count is incremented by one. If the resource is not free then the method returns false and the 
        thread is not blocked but it exits.
    - tryLock(long timeout, TimeUnit unit): As per the method, the thread waits for a certain time period as 
        defined by arguments of the method to acquire the lock on the resource before exiting.
    - lockInterruptibly(): This method acquires the lock if the resource is free while allowing for the thread 
        to be interrupted by some other thread while acquiring the resource. It means that if the current thread 
        is waiting for lock but some other thread requests the lock, then the current thread will be interrupted 
        and return immediately without acquiring lock.
    - getHoldCount(): This method returns the count of the number of locks held on the resource.
    - isHeldByCurrentThread(): This method returns true if the lock on the resource is held by the current thread.

Important Points
    - One can forget to call the unlock() method in the finally block leading to bugs in the program. Ensure that 
        the lock is released before the thread exits.
    - The intrinsic locks that we use with "synchronized" blocks are automatically released when the thread
        holding the lock exits the synchronized block. But in case of lock interface objects, we have to manually
        call unlock().
    - The fairness parameter used to construct the lock object decreases the throughput of the program.

The ReentrantLock is a better replacement for synchronization, which offers many features not provided by 
synchronized. However, the existence of these obvious benefits are not a good enough reason to always prefer 
ReentrantLock to synchronized. Instead, make the decision on the basis of whether you need the flexibility offered 
by a ReentrantLock.

------------------------------------------------------------------------------------------------------------------------------
PARAMETERS	                     SYNCHRONIZED               LOCK FRAMEWORK
------------------------------------------------------------------------------------------------------------------------------
Across Methods	                 Not possible               Yes, Locks can be implemented across the methods, you can invoke 
                                                            lock() in method1 and invoke unlock() in method2.	
try to acquire lock	             Not possible               Yes, trylock(timeout) method is supported by Lock framework, which 
                                                            will get the lock on the resource if it is available, else it returns 
                                                            false and Thread wont get blocked.	
Fair lock management	         Not possible               Yes, Fair lock management is available in case of lock framework. It 
                                                            hands over the lock to long waiting thread. Even in fairness mode set 
                                                            to true, if trylock is coded, it is served first.
List of waiting threads	         Not possible               Yes, List of waiting threads can be seen using Lock framework
Release of lock in exceptions	 Synchronized works         lock.lock(); myMethod();Lock.unlock();  ->  unlock() can't 
                                 It releases the lock       be executed in this code if any exception being thrown from myMethod().
------------------------------------------------------------------------------------------------------------------------------
*/

class MyProducer implements Runnable {
    private List<String> buffer;
    private String threadColor;
    private ReentrantLock bufferLock;

    MyProducer(List<String> buffer, String threadColor, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.threadColor = threadColor;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        String[] producedStrings = { "High in the halls of the kings who are gone", "Jenny would dance with her ghosts",
                "The ones she had lost and the ones she had found", "And the ones who had loved her the most." };
        for (String producedString : producedStrings) {
            System.out.println(threadColor + "Produced String: " + producedString);
            bufferLock.lock();
            try {
                buffer.add(producedString);
            } finally {
                bufferLock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferLock.lock();
        try {
            buffer.add("EOM");
        } finally {
            bufferLock.unlock();
        }
    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String threadColor;
    private ReentrantLock bufferLock;

    MyConsumer(List<String> buffer, String threadColor, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.threadColor = threadColor;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while (true) {
            bufferLock.lock();
            try {
                if (buffer.isEmpty()) {
                    continue;
                    /*
                     * In a real-world application, we wouldn't loop like this. We either sleep for
                     * a while before checking again or we will have a system that manages threads
                     * and uses the wait() and notify() methods.
                     */
                } else {
                    String stringToBeConsumed = buffer.get(0);
                    if (stringToBeConsumed.equals("EOM")) {
                        System.out.println(threadColor + "Exiting...");
                        return;
                        /*
                         * Consumer shouldn't remove "EOM" from the buffer; otherwise the rest of
                         * consumer threads will loop indefinitely not realizing that no more messages
                         * are going to come.
                         */
                    } else {
                        System.out.println(threadColor + "Consuming String: " + stringToBeConsumed);
                        buffer.remove(0);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                bufferLock.unlock();
            }
        }
    }
}

class Concurrency_08_ReentrantLocks {
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        MyProducer producer = new MyProducer(buffer, ThreadColor1.ANSI_RED, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor1.ANSI_YELLOW, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor1.ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class ThreadColor1 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001b[37m";
}
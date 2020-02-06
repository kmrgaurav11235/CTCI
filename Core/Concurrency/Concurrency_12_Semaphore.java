import java.util.concurrent.Semaphore;

/*
https://www.geeksforgeeks.org/semaphore-in-java/
https://www.youtube.com/watch?v=KxTRsvgqoVQ&list=PLBB24CFB073F1048E&index=13

- A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero, then access is allowed. 
    If it is zero, then access is denied. What the counter is counting, are permits that allow access to the shared resource. Thus, to access 
    the resource, a thread must be granted a permit from the semaphore.
- If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
    Otherwise, the thread will be blocked until a permit can be acquired.
- When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
    If there is another thread waiting for a permit, then that thread will acquire a permit at that time.
- Constructors in Semaphore class : There are two constructors in Semaphore class.
    1. Semaphore(int num)
    2. Semaphore(int num, boolean fairness)
    Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time. If it 
    is one, then only one thread can access the resource at any one time. By default, all waiting threads are granted a permit in an undefined order. 
    By setting fairness to true, you can ensure that waiting threads are granted a permit in the order in which they requested access. This does 
    affects the performance though.

https://stackoverflow.com/questions/2332765/lock-mutex-semaphore-whats-the-difference
https://stackoverflow.com/questions/34519/what-is-a-semaphore/40238#40238

Semaphore vs Locks
- A lock allows only one thread to enter the part that's locked and the lock is not shared with any other processes.
- A semaphore does the same as a lock but allows n number of threads to enter, this can be used, for example, to limit the number of cpu, io or ram intensive 
    tasks running at the same time.
- You must unlock() from the same thread you lock() from. But there is no such requirement for a Semaphore.
- The key point is that Locks should be used to protect shared resources, while semaphores should be used for signaling. You should generally not use 
    semaphores to protect shared resources, nor Locks for signaling.
- The most common (but nonetheless incorrect) answer to the question posed at the top is that Locks and semaphores are very similar, with the only significant 
    difference being that semaphores can count higher than one. Nearly all engineers seem to properly understand that a Lock is a binary flag used to protect 
    a shared resource by ensuring mutual exclusion inside critical sections of code.
- The correct use of a semaphore is for signaling from one thread (or task) to another. A Lock is meant to be taken and released, always in that order, by each 
    thread (or task) that uses the shared resource.
- Lock: resource sharing
- Semaphore: signaling
*/
class Concurrency_12_Semaphore {
    public static void main(String[] args) throws InterruptedException{
        Semaphore semaphore = new Semaphore(3);
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 3

        // release() increases the number of permits.
        semaphore.release();
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 4

        // acquire() decreases the number of permits. It also throws InterruptedException
        semaphore.acquire();
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 3

        semaphore.acquire();
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 2

        semaphore.acquire();
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 1

        semaphore.acquire();
        System.out.println("Current Permits: " + semaphore.availablePermits()); // 0

        semaphore.acquire(); // Since, no permits are available, this will just block the thread
    }
}
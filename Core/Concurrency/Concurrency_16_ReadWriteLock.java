import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
https://www.youtube.com/watch?v=7VqWkc9o7RM&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=15
https://codesjava.com/readwritelock-in-java
http://tutorials.jenkov.com/java-util-concurrent/readwritelock.html

- java.util.concurrent.locks.ReadWriteLock is an advanced thread lock mechanism. It allows multiple threads to read 
    a certain resource, but only one to write it, at a time.
- The idea is, that multiple threads can read from a shared resource without causing concurrency errors. The concurrency 
    errors first occur when reads and writes to a shared resource occur concurrently, or if multiple writes take place 
    concurrently.
- The rules by which a thread is allowed to lock the ReadWriteLock either for reading or writing the guarded resource, 
    are as follows:
    1. Read Lock - If no threads have locked the ReadWriteLock for writing, and no thread have requested a write lock 
        (but not yet obtained it). Here, multiple threads can lock the lock for reading.
    2. Write Lock - If no threads are reading or writing. Here, only one thread at a time can lock the lock for writing.


ReadWriteLock Implementations
- ReadWriteLock is an interface. The java.util.concurrent.locks package contains the ReadWriteLock implementation - 
    ReentrantReadWriteLock

*/
class ReaderImpl implements Runnable {
    private List<Integer> integerList;
    private ReadWriteLock readWriteLock;
    private Random random = new Random();

    public ReaderImpl(List<Integer> integerList, ReadWriteLock readWriteLock) {
        this.integerList = integerList;
        this.readWriteLock = readWriteLock;
    }

    private void read() {
        readWriteLock.readLock().lock();
        try {
            int index = random.nextInt(integerList.size());
            System.out.println("Reader Thread " + Thread.currentThread().getName() + ": Read data at index " + index
                    + ": " + integerList.get(index));
        } finally {
            readWriteLock.readLock().unlock();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while (integerList.size() < 10) {
            read();
        }
    }
}
class WriterImpl implements Runnable {
    private List<Integer> integerList;
    private ReadWriteLock readWriteLock;
    private Random random = new Random();

    public WriterImpl(List<Integer> integerList, ReadWriteLock readWriteLock) {
        this.integerList = integerList;
        this.readWriteLock = readWriteLock;
    }

    private void write() {
        readWriteLock.writeLock().lock();
        try {
            int newInt = random.nextInt(1000);
            integerList.add(newInt);
            System.out.println("Writer Thread " + Thread.currentThread().getName() + ": Wrote data : " + newInt);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while (integerList.size() < 10) {
            write();
        }
    }
}
class Concurrency_17_ReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        // Adding fairness to make sure one thread doesn't hogs the lock
        // This will affect performance though
        
        List<Integer> integerList = new ArrayList<>();

        ReaderImpl reader1 = new ReaderImpl(integerList, readWriteLock);
        ReaderImpl reader2 = new ReaderImpl(integerList, readWriteLock);
        WriterImpl writer1 = new WriterImpl(integerList, readWriteLock);
        WriterImpl writer2 = new WriterImpl(integerList, readWriteLock);

        ExecutorService pool = Executors.newFixedThreadPool(4);

        pool.submit(reader1);
        pool.submit(reader2);
        pool.submit(writer1);
        pool.submit(writer2);

        pool.shutdown();
    }
}
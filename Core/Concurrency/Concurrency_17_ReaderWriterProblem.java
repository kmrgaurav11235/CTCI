import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/*
https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
https://wiki.eecs.yorku.ca/course_archive/2007-08/F/6490A/readers-writers

- We cannot use Lock instead of Semaphore:
    You must unlock() from the same thread you lock() from. But there is no such requirement for a Semaphore.
*/
class MyReader implements Runnable {

    private List<Integer> buffer;
    private Semaphore readLock;
    private Semaphore writeLock;
    private Random random;
    private static volatile int readerCount = 0;

    MyReader(List<Integer> buffer, Semaphore readLock, Semaphore writeLock) {

        this.buffer = buffer;
        this.readLock = readLock;
        this.writeLock = writeLock;
        random = new Random();
    }

    @Override
    public void run() {
        while (buffer.size() <= 10) {
            // 1. Acquire Lock
            try {
                readLock.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                readerCount++;
                if (readerCount == 1) {
                    writeLock.acquire();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.release();
            }

            // 2. Read the common buffer
            if (buffer.size() > 0) {
                int randomIndex = random.nextInt(buffer.size());
                System.out.println("Reader " + Thread.currentThread().getName() + ": Reading index " + randomIndex
                        + " - " + buffer.get(randomIndex));
            }

            // 3. Release lock
            try {
                readLock.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                readerCount--;
                if (readerCount == 0) {
                    writeLock.release();
                }
            } finally {
                readLock.release();
            }

            // Sleep for a short time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class MyWriter implements Runnable {

    private List<Integer> buffer;
    private Semaphore writeLock;
    private Random random;

    MyWriter(List<Integer> buffer, Semaphore writeLock) {
        this.buffer = buffer;
        this.writeLock = writeLock;
        random = new Random();
    }

    @Override
    public void run() {
        while (buffer.size() <= 10) {
            try {
                writeLock.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                int randomInt = random.nextInt(1000);
                System.out.println("Writer " + Thread.currentThread().getName() + ": Writing number - " + randomInt
                        + " in the buffer.");
                buffer.add(randomInt);
            } finally {
                writeLock.release();
            }

            // Sleep for a short time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Concurrency_17_ReaderWriterProblem {
    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();
        Semaphore readLock = new Semaphore(1, true);
        Semaphore writeLock = new Semaphore(1, true);

        MyReader myReader1 = new MyReader(buffer, readLock, writeLock);
        MyReader myReader2 = new MyReader(buffer, readLock, writeLock);
        MyWriter myWriter1 = new MyWriter(buffer, writeLock);
        MyWriter myWriter2 = new MyWriter(buffer, writeLock);

        Thread myReaderThread1 = new Thread(myReader1, "reader1");
        Thread myReaderThread2 = new Thread(myReader2, "reader2");
        Thread myWriterThread1 = new Thread(myWriter1, "writer1");
        Thread myWriterThread2 = new Thread(myWriter2, "writer1");

        myWriterThread1.start();
        myWriterThread2.start();
        myReaderThread1.start();
        myReaderThread2.start();
    }
}
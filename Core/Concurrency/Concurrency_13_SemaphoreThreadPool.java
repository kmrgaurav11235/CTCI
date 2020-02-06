import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
https://www.geeksforgeeks.org/semaphore-in-java/
https://www.youtube.com/watch?v=KxTRsvgqoVQ&list=PLBB24CFB073F1048E&index=13
https://www.youtube.com/watch?v=shH38znT_sQ&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=17
*/
class SingletonConnection {
    // A connection class implementing singleton pattern, so that there is only
    // Connection object
    private static final SingletonConnection INSTANCE = new SingletonConnection();

    private static int connectionCounter = 0;
    // Counts the number of connections active right now

    private Semaphore semaphore = new Semaphore(10);
    // This semaphore will allow only 10 threads at a time.

    public static SingletonConnection getInstance() {
        return INSTANCE;
    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            doTask();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }        
    }

    private void doTask() throws Exception {
        synchronized (this) {
            connectionCounter++;
            System.out.println("Starting - Number of connections: " + connectionCounter);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        synchronized(this) {
            connectionCounter--;
            System.out.println("Ending - Number of connections: " + connectionCounter);
        }

    }
}
class Concurrency_13_SemaphoreThreadPool {
    public static void main(String[] args){

        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            // Create and submit 50 Runnables to thread-pool
            pool.submit(new Runnable(){
                @Override
                public void run() {
                    SingletonConnection instance = SingletonConnection.getInstance();
                    instance.connect();
                }
            });
        }
        pool.shutdown();
    }
}
import java.util.Arrays;

/*
- We create a new class which implements java.lang.Runnable interface and override run() method. 
    Then we instantiate a Thread object and call start() method on this object.
- Thread Class vs Runnable Interface:
    1. If we extend the Thread class, our class cannot extend any other class because Java doesn’t 
        support multiple inheritance. But, if we implement the Runnable interface, our class can 
        still extend other base classes.
    2. We can achieve basic functionality of a thread by extending Thread class because it provides 
        some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable 
        interface.
    3. Runnable interface works well with lambda.
*/
class MyRunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("Inside MyRunnableImpl. Thread Id" + Thread.currentThread().getId() + ". Thread Name: "
                + Thread.currentThread().getName() + ".");
    }
}

class Concurrency_03_ImplementingRunnableInterface {
    public static void main(String[] args) {
        // Main Thread
        System.out.println("Inside Main Thread. Thread ID: " + Thread.currentThread().getId() + ", Thread Name: "
                + Thread.currentThread().getName() + ".");

        // Class implementing Runnable
        Thread thread = new Thread(new MyRunnableImpl());
        thread.start();

        // Anonymous Class implementing runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Anonymous Thread. Thread ID: " + Thread.currentThread().getId()
                        + ", Thread Name: " + Thread.currentThread().getName() + ".");
            }
        }).start();

        // Lambda
        new Thread(() -> {
            System.out.println("Inside Lambda Thread. Thread ID: " + Thread.currentThread().getId() + ", Thread Name: "
                    + Thread.currentThread().getName() + ".");
        }).start();

        // Put the main thread to sleep
        try {
            Thread.sleep(3000);
            System.out.println("Hello again from Main Thread. 3 seconds have passed. Thread ID: " 
                + Thread.currentThread().getId() + ", Thread Name: "
                + Thread.currentThread().getName() + ".");
        } catch (InterruptedException e) {
            System.out.println("Another thread interrupted me. " + Arrays.toString(e.getStackTrace()));
        }
    }
}
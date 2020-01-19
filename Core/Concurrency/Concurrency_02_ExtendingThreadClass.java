/*
https://www.geeksforgeeks.org/multithreading-in-java/
We create a class that extends the java.lang.Thread class. This class overrides the run() method 
available in the Thread class. A thread begins its life inside run() method. We create an object of 
our new class and call start() method to start the execution of a thread. start() invokes the run() 
method on the Thread object.
*/
class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from AnotherThread. Thread ID: " 
            + Thread.currentThread().getId() + ", Thread Name: "
            + Thread.currentThread().getName() + ".");
    }
}
class Concurrency_02_ExtendingThreadClass {
    public static void main(String[] args) {
        // Main Thread
        System.out.println("Hello from Main Thread. Thread ID: " 
            + Thread.currentThread().getId() + ", Thread Name: "
            + Thread.currentThread().getName() + ".");
        
        // Class extending Thread class
        AnotherThread anotherThread = new AnotherThread();
        anotherThread.start();

        // Anonymous Class
        new Thread() {
            @Override
            public void run() {
                System.out.println("Hello from Anonymous Class Thread. Thread ID: " 
                    + Thread.currentThread().getId() + ", Thread Name: "
                    + Thread.currentThread().getName() + ".");
            }
        }.start();
        // Main Thread again
        System.out.println("Hello again from Main Thread. Thread ID: " 
            + Thread.currentThread().getId() + ", Thread Name: "
            + Thread.currentThread().getName() + ".");
    }
}
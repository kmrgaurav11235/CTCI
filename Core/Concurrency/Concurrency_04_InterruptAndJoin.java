/*
https://www.geeksforgeeks.org/lifecycle-and-states-of-a-thread-in-java/
https://www.geeksforgeeks.org/java-concurrency-yield-sleep-and-join-methods/
https://www.geeksforgeeks.org/how-a-thread-can-interrupt-an-another-thread-in-java/

- sleep(): This method causes the currently executing thread to sleep for the specified number of 
    milliseconds, subject to the precision and accuracy of system timers and schedulers.
- interrupt() : If any thread is in sleeping or waiting state then using interrupt() method, we 
    can interrupt the execution of that thread by showing InterruptedException. 
- A thread which is:
    1) In the sleeping state because of Thread.sleep() method, or 
    2) Waiting state due to join() method, or 
    3) Is running normally 
  can be interrupted with the help of interrupt() method of Thread class.
- join(): The join() method of a Thread instance is used to join the start of a thread’s execution 
    to end of other thread’s execution such that a thread does not start running until another 
    thread ends. If join() is called on a Thread instance, the currently running thread will wait 
    until the provided Thread instance has finished executing.
- Giving a timeout within join() will make the join() effect to be nullified after the specific 
    timeout. The join() method waits at most this much milliseconds for this thread to die. A 
    timeout of 0 means to wait forever.
*/
class Concurrency_04_InterruptAndJoin {
    public static void main(String[] args) {
        Thread sleepingThread = new Thread(() -> {
            System.out.println("Inside sleeping thread. Thread id: " + Thread.currentThread().getId() + ".");
            System.out.println("Inside sleeping thread. Going to sleep.");

            // Now this thread sleeps for 10 sec.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Inside sleeping thread. Another thread interrupted me. Thread id: " 
                    + Thread.currentThread().getId() + ".");
                return;
                /*
                 * In the program, we handle the InterruptedException using try and catch block,
                 * so whenever any thread interrupt the currently executing thread, it will
                 * comes out from the sleeping state but it will not stop working. So, the
                 * return statement is here to ensure the 're-awoken' thread stops.
                 */
            }
        });

        sleepingThread.start();
        // Both sleepingThread and main thread are now running
        System.out.println("Inside main thread. Thread id: " + Thread.currentThread().getId() + ".");

        Thread waitingThread = new Thread(() -> {
            System.out.println("Inside Waiting Thread. Thread id: " + Thread.currentThread().getId() + ".");
            System.out.println("Inside Waiting Thread. I will now wait for sleepingThread to complete.");
            try {
                sleepingThread.join();
            } catch (InterruptedException e) {
                System.out.println("Inside Waiting Thread. I was interrupted while waiting.");
            }
            System.out.println("Inside Waiting Thread. sleepingThread must be now complete.");
        });
        waitingThread.start();

        // Interrupting the sleepingThread
        sleepingThread.interrupt();
    }
}
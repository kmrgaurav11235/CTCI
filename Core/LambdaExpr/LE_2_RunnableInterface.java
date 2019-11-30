/*
Lambda expressions work with in-built interfaces with single unimplemented methods too.
*/
class LE_2_RunnableInterface {
    public static void main(String[] args) {
        System.out.println("Hello from thread: " + Thread.currentThread().getName() + ".");

        new Thread(
            () -> 
            System.out.println("Hello from thread: " + Thread.currentThread().getName() + ".")
            ).start();
        /* 
        Signature is new Thread(Runnable runnable) and start call on this Thread object.
        The compiler knows that there is only one Thread class single argument constructor and it
        takes Runnable argument. It also knows that the Runnable interface as a single unimplemented
        method - run(). Also, run() method takes no parameter. So, it is able to match the lambda expr
        with Runnable.
        */
    }
}
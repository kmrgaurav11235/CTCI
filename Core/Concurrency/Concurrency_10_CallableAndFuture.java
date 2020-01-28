import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
https://www.geeksforgeeks.org/callable-future-java/
- There are two ways of creating threads – one by extending the Thread class and other by creating a thread 
with a Runnable. However, one feature lacking in  Runnable is that we cannot make a thread return result when 
it terminates, i.e. when run() completes. For supporting this feature, the Callable interface is present in Java.

Callable vs Runnable
- For implementing Runnable, the run() method needs to be implemented which does not return anything, while for a 
Callable, the call() method needs to be implemented which returns a result on completion. Note that a thread 
can’t be created with a Callable, it can only be created with a Runnable.
- Another difference is that the call() method can throw an exception whereas run() cannot.
- Method signature that has to be overridden for implementing Callable.
    public Object call() throws Exception;

Future
- When the call() method completes, answer must be stored in an object known to the main thread, so that the main 
thread can know about the result that the thread returned. How will the program store and obtain this result later? 
For this, a Future object can be used. Think of a Future as an object that holds the result – it may not hold it 
right now, but it will do so in the future (once the Callable returns). Thus, a Future is basically one way the 
main thread can keep track of the progress and result from other threads. To implement this interface, 5 methods 
have to be overridden, but as the example below uses a concrete implementation from the library, only the important 
methods are listed here.

- Observe that Callable and Future do two different things – Callable is similar to Runnable, in that it encapsulates 
a task that is meant to run on another thread, whereas a Future is used to store a result obtained from a different 
thread. In fact, the Future can be made to work with Runnable as well, which is something that will become clear when 
Executors come into the picture.

1. public boolean cancel(boolean mayInterrupt): Used to stop the task. It stops the task if it has not started. If it 
    has started, it interrupts the task only if mayInterrupt is true.
2. public Object get() throws InterruptedException, ExecutionException: Used to get the result of the task. If the task 
    is complete, it returns the result immediately, otherwise it waits till the task is complete and then returns the result.
3. public boolean isDone(): Returns true if the task is complete and false otherwise

- To create the thread, a Runnable is required. To obtain the result, a Future is required.

- The Java library has the concrete type FutureTask, which implements Runnable and Future, combining both functionality 
conveniently.
- A FutureTask can be created by providing its constructor with a Callable. Then the FutureTask object is provided to 
the constructor of Thread to create the Thread object. Thus, indirectly, the thread is created with a Callable. For 
further emphasis, note that there is no way to create the thread directly with a Callable.
*/
class CallableExample implements Callable<Integer> {
    private String name;
    private String color;

    CallableExample(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int info = random.nextInt(101);

        Thread.sleep(2000);
        System.out.println(color + "In " + name + ". Returning: " + info + ".");
        return info;
    }
}

class Concurrency_10_CallableAndFuture {
    public static void main(final String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CallableExample ce1 = new CallableExample("Callable Example 1", ThreadColor.ANSI_YELLOW);
        CallableExample ce2 = new CallableExample("Callable Example 2", ThreadColor.ANSI_WHITE);
        CallableExample ce3 = new CallableExample("Callable Example 3", ThreadColor.ANSI_RED);
        CallableExample ce4 = new CallableExample("Callable Example 4", ThreadColor.ANSI_PURPLE);
        CallableExample ce5 = new CallableExample("Callable Example 5", ThreadColor.ANSI_GREEN);

        // submit() method allows us get result
        Future<Integer> future1 = pool.submit(ce1);
        Future<Integer> future2 = pool.submit(ce2);
        Future<Integer> future3 = pool.submit(ce3);
        Future<Integer> future4 = pool.submit(ce4);
        Future<Integer> future5 = pool.submit(ce5);

        // To get the result, call the Future.get() method. It throws InterruptedException (is thread was interrupted)
        // and ExecutionException (if the computation threw an exception). This method blocks till result is available.
        try {
            System.out.println(ThreadColor.ANSI_RESET + "Future 1: " + future1.get() + 
                    "\nFuture 2: " + future2.get() + 
                    "\nFuture 3: " + future3.get() + 
                    "\nFuture 4: " + future4.get() + 
                    "\nFuture 5: " + future5.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
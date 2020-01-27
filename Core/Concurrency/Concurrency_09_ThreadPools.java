import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
https://www.geeksforgeeks.org/thread-pools-java/
- Server Programs such as database and web servers repeatedly execute requests from multiple clients and these 
are oriented around processing a large number of short tasks. An approach for building a server application 
would be to create a new thread each time a request arrives and service this new request in the newly created 
thread. While this approach seems simple to implement, it has significant disadvantages. A server that creates 
a new thread for every request would spend more time and consume more system resources in creating and destroying 
threads than processing actual requests.

- Since active threads consume system resources, a JVM creating too many threads at the same time can cause the 
system to run out of memory. This necessitates the need to limit the number of threads being created.

- What is ThreadPool in Java?
A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of 
thread cycle overhead and resource thrashing. Since the thread is already existing when the request arrives, the 
delay introduced by thread creation is eliminated, making the application more responsive.

- Java provides the Executor framework which is centered around the Executor interface, its sub-interface – 
ExecutorService and the class - ThreadPoolExecutor, which implements both of these interfaces. By using the executor, 
one only has to implement the Runnable objects and send them to the executor to execute.
- They allow you to take advantage of threading, but focus on the tasks that you want the thread to perform, instead 
of thread mechanics.
- To use thread pools, we first create a object of ExecutorService and pass a set of tasks to it. ThreadPoolExecutor 
class allows to set the core and maximum pool size.The runnables that are run by a particular thread are executed sequentially.

Executor Thread Pool Methods:
----------------------------------------------------------------------------------
Method                            Description
----------------------------------------------------------------------------------
newFixedThreadPool(int)           Creates a fixed size thread pool.
newCachedThreadPool()             Creates a thread pool that creates new 
                                  threads as needed, but will reuse previously 
                                  constructed threads when they are available
newSingleThreadExecutor()         Creates a single thread. 
----------------------------------------------------------------------------------

In case of a fixed thread pool, if all threads are being currently run by the executor then the pending tasks are placed in
a queue and are executed when a thread becomes idle.

Risks in using Thread Pools
- Deadlock : While deadlock can occur in any multi-threaded program, thread pools introduce another case of deadlock, one in 
    which all the executing threads are waiting for the results from the blocked threads waiting in the queue due to the 
    unavailability of threads for execution.
- Thread Leakage :Thread Leakage occurs if a thread is removed from the pool to execute a task but not returned to it when the 
    task completed. As an example, if the thread throws an exception and pool class does not catch this exception, then the 
    thread will simply exit, reducing the size of the thread pool by one. If this repeats many times, then the pool would 
    eventually become empty and no threads would be available to execute other requests.
- Resource Thrashing :If the thread pool size is very large then time is wasted in context switching between threads. Having 
    more threads than the optimal number may cause starvation problem leading to resource thrashing as explained.

Important Points
- Don’t queue tasks that concurrently wait for results from other tasks. This can lead to a situation of deadlock as described above.
- Be careful while using threads for a long lived operation. It might result in the thread waiting forever and would eventually 
    lead to resource leakage.
- The Thread Pool has to be ended explicitly at the end. If this is not done, then the program goes on executing and never ends. Call 
    shutdown() on the pool to end the executor. If you try to send another task to the executor after shutdown, it will throw a 
    RejectedExecutionException.
- One needs to understand the tasks to effectively tune the thread pool. If the tasks are very contrasting then it makes sense to 
    use different thread pools for different types of tasks so as to tune them properly.

Tuning Thread Pool
- The optimum size of the thread pool depends on the number of processors available and the nature of the tasks. On a N processor 
    system for a queue of only computation type processes, a maximum thread pool size of N or N+1 will achieve the maximum 
    efficiency.But tasks may wait for I/O and in such a case we take into account the ratio of waiting time(W) and service 
    time(S) for a request; resulting in a maximum pool size of N*(1+ W/S) for maximum efficiency.
- The thread pool is a useful tool for organizing server applications. It is quite straightforward in concept, but there are several 
    issues to watch for when implementing and using one, such as deadlock, resource thrashing. Use of executor service makes it easier 
    to implement.
*/

class Task implements Runnable {
    String name;
    String color;
    long executionTimeInMilliseconds;

    Task(String name, String color) {
        this.name = name;
        this.color = color;
        this.executionTimeInMilliseconds = new Random().nextInt(2000);
    }

    @Override
    public void run() {
        System.out.println(color + "Task: " + name + " starting at " + new Date() + ". Execution time in milliseconds: "
                + executionTimeInMilliseconds);
        try {
            Thread.sleep(executionTimeInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(color + "Task: " + name + " completing at " + new Date());
    }
}
class Concurrency_09_ThreadPools {
    public static void main(String[] args) {
        // Tasks
        Task task1 = new Task("Task 1", ThreadColor.ANSI_BLUE);
        Task task2 = new Task("Task 2", ThreadColor.ANSI_CYAN);
        Task task3 = new Task("Task 3", ThreadColor.ANSI_GREEN);
        Task task4 = new Task("Task 4", ThreadColor.ANSI_PURPLE);
        Task task5 = new Task("Task 5", ThreadColor.ANSI_RED);

        // Pool
        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.execute(task1);
        pool.execute(task2);
        pool.execute(task3);
        pool.execute(task4);
        pool.execute(task5);

        // Shut down
        pool.shutdown();
        /*
        As seen in the execution of the program, the task 4 or task 5 are executed only when a thread in the pool becomes 
        idle. Until then, the extra tasks are placed in a queue.
        */
    }
}
/*
Terms:
1) Process: A process is a unit of execution that has its own memory space.
    - Each instance of JVM runs as a process (this is not true for all JVM implementations but for 
        most of them).
    - When we run a Java Console application (or any other type of Java application), we are kicking 
        off a process.
    - Each Java application is its own process. If two are running simultaneously, each has its own
        memory - 'Heap', which they don't share with the other.
2) Thread: A Thread is a unit of execution within a process. So, threads are light-weight processes 
    within a process.
    - Each process can have multiple threads.
    - In Java, each process (or application) has at least one thread - the 'Main' thread. Our code runs
         on the main thread and other threads that we explicitly create.
    - Creating threads does not requires as many resources as creating a process. 
    - Every thread created by a process shares that processes memory (Heap) and files.
    - In addition to the process memory (Heap), each thread has a 'Thread Stack'. It is the memory 
        that only that particular thread can access.
    - In Java, we might want to perform a task that takes a long time, e.g. querying a database located
        in cloud. We could do this on the main thread, but the code within each Main thread executes in
        a linear fashion. The Main thread will not be able to anything else while it is waiting for the 
        data.
    - So, instead of tying up the Main thread, we can create another thread and execute the long running 
        task on that. This would free up the Main thread so that it can continue executing.
3) Concurrency: Concurrency refers to an application doing more than one thing at a time - progress can
    be made on more than one task.
    - Concurrency means that one doesn't have to finish before another can start.
4) Multithreading: Multithreading is a Java feature that allows concurrent execution of two or more  
    threads for maximum utilization of CPU. 
    - Threads can be created by using two mechanisms :
        1. Extending the Thread class
        2. Implementing the Runnable Interface
*/
class Concurrency_01_Introduction {

}
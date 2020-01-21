/*
- Every thread has a Thread stack that only that thread can access, i.e. one thread cannot access 
    another thread's stack. But the heap between them is common (it is the process heap) and they 
    can both access it.
- Local variables are stored on the Thread Stack. So, each thread has its own copy of Local Variable.
- Instance variables are stored on Heap. So, when multiple threads work on the same instance variable,
    they share it. They don't have their own copy, so when one thread changes the value of an object
    instance variable, another thread will see the new value from that point.
- When two or more threads are trying to write/update a shared resource, it is called Race Condition.
*/
class Concurrency_05_MultipleThreads {
    public static void main(String[] args) {
        CountDown countDown = new CountDown();

        CountDownThread t1 = new CountDownThread(countDown);
        t1.setName("Thread 1");
        CountDownThread t2 = new CountDownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class CountDownThread extends Thread {
    private CountDown countDown;

    CountDownThread(CountDown countDown) {
        this.countDown = countDown;
    }
    @Override
    public void run() {
        countDown.doCountDown();
    }
}

class CountDown {
    int i;
    /*
    USING THE INSTANCE VARIABLE FOR COUNTDOWN:
    OUTPUT:
    ********************
    Thread 1: i = 10
    Thread 1: i = 9
    Thread 2: i = 10
    Thread 1: i = 8
    Thread 2: i = 7
    Thread 1: i = 6
    Thread 2: i = 5
    Thread 1: i = 4
    Thread 2: i = 3
    Thread 1: i = 2
    Thread 2: i = 1
    ********************
    Both threads put common 10-1, interleaving with each other. Sometimes, they both print the first 
    number (10). Since instance variables are stored in Heap and shared between threads, this makes 
    sense. 
    Output can appear out of order as a thread can be suspended and give way to other thread at any 
    step: just after it has decremented i but before the condition has been checked yet, after it 
    formed the string for sysout but hasn't performed the output yet etc.
    */
    public void doCountDown() {
        String color;

        switch (Thread.currentThread().getName()) {
        case "Thread 1":
            color = ThreadColor.ANSI_CYAN;
            break;
        case "Thread 2":
            color = ThreadColor.ANSI_PURPLE;
            break;
        default:
            color = ThreadColor.ANSI_WHITE;
            break;
        }

        /*
        USING THE INSTANCE VARIABLE FOR COUNTDOWN:
        OUTPUT:
        ********************
        Thread 1: i = 10
        Thread 2: i = 10
        Thread 1: i = 9
        Thread 2: i = 9
        Thread 2: i = 8
        Thread 1: i = 7
        Thread 2: i = 7
        Thread 1: i = 6
        Thread 2: i = 6
        Thread 1: i = 5
        Thread 2: i = 5
        Thread 1: i = 4
        Thread 2: i = 4
        Thread 1: i = 3
        Thread 2: i = 3
        Thread 1: i = 2
        Thread 2: i = 2
        Thread 1: i = 1
        Thread 2: i = 1
        ********************
        Both threads print their own 10-1, interleaving with each other.
        Since each thread has the copy of local 'i' on its own stack, this makes sense.
        */
        // int i;
        for (i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
        }
        
    }
}

class ThreadColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001b[37m";
}
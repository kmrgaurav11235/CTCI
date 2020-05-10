import java.util.Random;

/*
https://www.baeldung.com/java-dining-philoshophers

- There are n silent philosophers (P1 – Pn) sitting around a circular table, spending their lives 
    eating and thinking. There are n forks for them to share (1 – n). To be able to eat, a 
    philosopher needs to have forks in both his hands. After eating, he puts both of them down and 
    then they can be picked by another philosopher who repeats the same cycle.
- The goal is to come up with a protocol that helps the philosophers achieve their goal of eating 
    and thinking without getting starved to death.
- Dijkstra first formulated this problem and presented it regarding computers accessing tape drive 
    peripherals. The present formulation was given by Tony Hoare, who is also known for inventing 
    the quicksort sorting algorithm.
- An initial solution would be to make each of the philosophers follow the following protocol:
    {Pseudo-code}
    while(true) { 
        // Initially, thinking about life, universe, and everything
        think();
    
        // Take a break from thinking, hungry now
        pick_up_left_fork();
        pick_up_right_fork();
        eat();
        put_down_right_fork();
        put_down_left_fork();
    
        // Not hungry anymore. Back to thinking!
    }
    {/Pseudo-code}
- As the above pseudo code describes, each philosopher is initially thinking. After a certain amount 
    of time, the philosopher gets hungry and wishes to eat. 
- At this point, he reaches for the forks on his either side and once he's got both of them, proceeds 
    to eat. Once the eating is done, the philosopher then puts the forks down, so that they're available 
    for his neighbor.
*/
class Fork {
    private int id;

    Fork(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fork" + id;
    }
}
class Philosopher implements Runnable {
    /*
    We model each of our philosophers as classes that implement the Runnable interface so that we 
    can run them as separate threads. Each Philosopher has access to two forks.
    */
    private String name;
    private Fork firstFork;
    private Fork secondFork;
    private Random random;

    Philosopher(String name, Fork firstFork, Fork secondFork) {
        this.name = name;
        this.firstFork = firstFork;
        this.secondFork = secondFork;
        random = new Random();
    }
    private void doAction(String action) {
        // Method that instructs a Philosopher to perform an action and prints in on the console.
        System.out.println("Philosopher " + name + ": " + action);
        try {
            // Each action is simulated by suspending the invoking thread for a random amount of time
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            System.err.println("Exception in: Philosopher " + name + " while performing " + action);
        }
    }
    public String getName() {
        return name;
    }
    public Object getFirstFork() {
        return firstFork;
    }
    public Object getSecondFork() {
        return secondFork;
    }

    @Override
    public void run() {
        /* 
        We use the synchronized keyword to acquire the internal monitor of the fork object and 
        prevent other threads from doing the same.
        */
        while (true) {
            // Think
            doAction("Thinking.");

            // Hungry
            synchronized (firstFork) {
                doAction("Pick up fork: " + firstFork);
                synchronized(secondFork) {
                    doAction("Pick up fork: " + secondFork);
                    doAction("Eating.");
                    doAction("Put down fork: " + secondFork);
                }
                doAction("Put down fork: " + firstFork);
            }
        }
    }

}
public class Concurrency_18_DiningPhilosophers {
    public static void main(String[] args) {
        int n = 6;
        String [] philosopherNames = {"Lao Tzu", "Sun Tzu", "Confucius", "Nietzsche", "Socrates", "Voltaire"};

        Fork [] forks = new Fork[n];
        for (int i = 0; i < n; i++) {
            forks[i] = new Fork(i);
        }

        /* 
        - To kick start the whole process, we write a client that creates 6 Philosophers as threads and 
            starts all of them.
        - Deadlock: If each of the Philosophers has acquired his left fork first, a situation can arise
            where he can't acquire his right fork, because his neighbor has already acquired it. This 
            situation is commonly known as the "Circular Wait" and is one of the conditions that results 
            in a "Deadlock". It prevents the progress of the system.
        - The primary reason for a deadlock is the "Circular Wait" condition where each process waits upon 
            a resource that's being held by some other process. Hence, to avoid a deadlock situation we 
            need to make sure that the circular wait condition is broken. There are several ways to achieve 
            this, the simplest one being the follows:

            - All Philosophers reach for their left fork first, except one who first reaches for his right fork.
        */  
        Philosopher [] philosophers = new Philosopher[n];
        for (int i = 1; i < n; i++) {
            philosophers[i] = new Philosopher(philosopherNames[i], forks[i], forks[(i + 1) % n]);
            new Thread(philosophers[i]).start();
        }
        philosophers[0] = new Philosopher(philosopherNames[0], forks[4], forks[0]);
        new Thread(philosophers[0]).start();
    }
}
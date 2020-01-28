import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/*
https://www.geeksforgeeks.org/arrayblockingqueue-class-in-java/
- ArrayBlockingQueue class is a bounded blocking queue backed by an array. By bounded it means that the size of the Queue 
is fixed. Once created, the capacity cannot be changed. Attempts to put an element into a full queue will result in the 
operation blocking. Similarly attempts to take an element from an empty queue will also be blocked. 
- Boundness of the ArrayBlockingQueue can be achieved initially by passing capacity as the parameter in the constructor of 
ArrayBlockingQueue. 
- This queue orders elements FIFO (first-in-first-out). It means that the head of this queue is the oldest element of the 
elements present in this queue. The tail of this queue is the newest element of the elements of this queue. The newly 
inserted elements are always inserted at the tail of the queue, and the queue retrieval operations obtain elements at the 
head of the queue.
- This class and its iterator implement all of the optional methods of the Collection and Iterator interfaces. This class 
is a member of the Java Collections Framework.

- Others members of java.util.Concurrent:
- Queue:
    1. ArrayBlockingQueue
    2. ConcurrentLinkedQueue
    3. LinkedBlockingQueue
    4. LinkedTransferQueue
    5. PriorityBlockingQueue
- Deque:
    1. ConcurrentLinkedDeque
    2. LinkedBlockingDeque
- Set:
    1. ConcurrentSkipListSet
    2. CopyOnWriteArraySet
    3. LinkedHashSet
- HashMap:
    1. ConcurrentHashMap

- Vector (List), Stack (Vector) and Hashtable (Map) are also synchronized, though they are a part of java.util package not
java.util.concurrent.

Class Hierarchy:

java.lang.Object 
    ↳ java.util.AbstractCollection 
        ↳ java.util.AbstractQueue 
            ↳ java.util.concurrent.ArrayBlockingQueue

Type Parameters: The type parameter of ArrayBlockingQueue E is the type of elements held in this collection

Constructor Summary:

- ArrayBlockingQueue(int capacity): Creates an ArrayBlockingQueue with the given (fixed) capacity and default access policy.
- ArrayBlockingQueue(int capacity, boolean fair): Creates an ArrayBlockingQueue with the given (fixed) capacity and the specified 
    access policy. If the fair value is if true then queue accesses for threads blocked on insertion or removal, are processed in 
    FIFO order; if false the access order is unspecified.
- ArrayBlockingQueue(int capacity, boolean fair, Collection c): Creates an ArrayBlockingQueue with the given (fixed) capacity, the 
    specified access policy and initially containing the elements of the given collection, added in traversal order of the collection’s 
    iterator. 

Method Summary:

- boolean add(E e): Inserts the specified element at the tail of this queue if it is possible to do so immediately without exceeding 
    the queue’s capacity, returning true upon success and throwing an IllegalStateException if this queue is full.
- void clear(): Atomically removes all of the elements from this queue.
- boolean contains(Object o): Returns true if this queue contains the specified element.
- int drainTo(Collection c): Removes all available elements from this queue and adds them to the given collection.
- Iterator iterator(): Returns an iterator over the elements in this queue in proper sequence.
- boolean offer(E e): Inserts the specified element at the tail of this queue if it is possible to do so immediately without exceeding 
    the queue’s capacity, returning true upon success and false if this queue is full. There is time-out version too.
- E peek(): Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
- E poll(): Retrieves and removes the head of this queue, or returns null if this queue is empty.  There is time-out version too.
- void put(E e): Inserts the specified element at the tail of this queue, waiting for space to become available if the queue is full.
- int remainingCapacity(): Returns the number of additional elements that this queue can ideally (in the absence of memory or resource 
    constraints) accept without blocking.
- boolean remove(Object o): Removes a single instance of the specified element from this queue, if it is present.
- int size(): Returns the number of elements in this queue.
- E take(): Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
- Object[] toArray(): Returns an array containing all of the elements in this queue, in proper sequence.
- String toString(): Returns a string representation of this collection.
*/
class ProducerABQ implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public ProducerABQ(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        String[] producedStrings = { "A star was bound upon her brows,", "A light was on her hair",
                "As sun upon the golden boughs", "In Lorien the fair." };
        for (String producedString : producedStrings) {
            try {
                // Put waits for space to become available if the queue is full
                buffer.put(producedString);
                System.out.println(color + "Produced String: " + producedString);

                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            buffer.put("EOM");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumerABQ implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public ConsumerABQ(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                /*
                Even though we are using ArrayBlockingQueue, we have to add this synchronized to make sure that we
                don't switch to another consumer thread between 'else if ("EOM".equals(buffer.peek())) == false' and 
                the else-block.
                */
                if (buffer.isEmpty()) {
                    continue;
                } else if ("EOM".equals(buffer.peek())) {
                    System.out.println(color + "Exiting...");
                    break;
                } else {
                    try {
                        // take() waits if necessary until an element becomes available.
                        String retrievedString = buffer.take();
                        System.out.println(color + "Consumed String: " + retrievedString);
    
                        Thread.sleep(new Random().nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
class Concurrency_11_ProducerConsumerUsingArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(3);

        ProducerABQ producer = new ProducerABQ(buffer, ThreadColor1.ANSI_RED);
        ConsumerABQ consumer1 = new ConsumerABQ(buffer, ThreadColor1.ANSI_YELLOW);
        ConsumerABQ consumer2 = new ConsumerABQ(buffer, ThreadColor1.ANSI_CYAN);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
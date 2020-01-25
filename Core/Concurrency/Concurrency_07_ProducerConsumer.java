import java.util.Random;

/*
https://www.geeksforgeeks.org/producer-consumer-solution-using-threads-java/
https://www.geeksforgeeks.org/inter-thread-communication-java/

What is Polling and what are problems with it?
 - The process of testing a condition repeatedly till it becomes true is known as polling.
 - Polling is usually implemented with the help of loops to check whether a particular condition is true 
    or not. If it is true, certain action is taken. This waste many CPU cycles and makes the 
    implementation inefficient.
- For example, in a classic queuing problem where one thread is producing data and other is consuming it.

How Java multi threading tackles this problem?
- To avoid polling, Java uses three methods, namely, wait(), notify() and notifyAll().
- All these methods belong to object class as final so that all classes have them. They must be used within 
    a synchronized block only.

    1. wait()-It tells the calling thread to give up the lock and go to sleep until some other thread enters 
        the same monitor and calls notify().
    2. notify()-It wakes up one single thread that called wait() on the same object. It should be noted that 
        calling notify() does not actually give up a lock on a resource.
    3. notifyAll()-It wakes up all the threads that called wait() on the same object.

Producer-Consumer solution using threads in Java
- In computing, the producer-consumer problem (also known as the bounded-buffer problem) is a classic example 
    of a multi-process synchronization problem. The problem describes two processes, the producer and the 
    consumer, which share a common, fixed-size buffer used as a queue.
- The producer’s job is to generate data, put it into the buffer, and start again.
- At the same time, the consumer is consuming the data (i.e. removing it from the buffer), one piece at a time.
- Problem: To make sure that the producer won’t try to add data into the buffer if it’s full and that the consumer 
    won’t try to remove data from an empty buffer.
- Solution: The producer is to either go to sleep or discard data if the buffer is full. The next time the consumer 
    removes an item from the buffer, it notifies the producer, who starts to fill the buffer again. In the same way, 
    the consumer can go to sleep if it finds the buffer to be empty. The next time the producer puts data into the 
    buffer, it wakes up the sleeping consumer.
- An inadequate solution could result in a deadlock where both processes are waiting to be awakened. 

Notes about the code:
- Q: Why use while for waiting? Why not use if?
- A: Always call wait in a for/while loop that is testing the condition that you are waiting on. This is because
    when a thread is notified to wake up, there is no guarantee that it is being woken up because the condition 
    that it was waiting on, has changed. It is possible that the OS has woken it up for another reason. So, we 
    always want to call wait() in a loop so that when it wakes up, it checks the condition again and goes back
    to wait() if the condition has not changed.

- Q: Why use notifyAll() rather than notify()?
- A: We cannot notify a specific thread (as notify doesn't accepts any parameter). Because of this, it is 
    conventional to use notifyAll() unless we are dealing with a situation with a significant number of threads 
    that are waiting for the same lock.
*/
class Message {
    private String message;
    private boolean isEmpty;

    Message() {
        this.isEmpty = true;
    }

    public synchronized void write(String message) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Exception while waiting in write() method. " + e);
            }
        }
        this.message = message;
        System.out.println("Writing message: " + message);
        this.isEmpty = false;
        notifyAll();
    }

    public synchronized String read() {
        while (isEmpty) {
            try {
                wait();
            } catch (Exception e) {
                System.err.println("Exception while waiting in read() method. " + e);
            }
        }
        System.out.println("Reading message: " + message);
        isEmpty = true;
        notifyAll();
        return message;
    }
}

class Producer implements Runnable {
    private Message message;

    Producer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String producedStrings[] = { "Humpty Dumpty sat on a wall", "Humpty Dumpty had a great fall.",
                "All the king's horses and all the king's men", "Couldn't put Humpty together again." };
        Random random = new Random();

        for (String producedString : producedStrings) {
            message.write(producedString);
            int sleepTime = random.nextInt(2000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.println("Exception while sleeping in Producer. " + e);
            }
        }

        message.write("EOM");
    }
}

class Consumer implements Runnable {
    private Message message;

    Consumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String readMessage = message.read(); !readMessage.equals("EOM"); readMessage = message.read()) {
            int sleepTime = random.nextInt(2000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.println("Exception while sleeping in Consumer. " + e);
            }
        }
    }
}
class Concurrency_07_ProducerConsumer {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();
    }
}
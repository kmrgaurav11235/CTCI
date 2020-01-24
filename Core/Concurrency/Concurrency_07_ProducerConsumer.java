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
*/
class Message {
    String msg;
    boolean isEmpty;

    Message(String msg, boolean isEmpty) {
        this.msg = msg;
        this.isEmpty = isEmpty;
    }

    public void sendMessage(String msg) {
        this.msg = msg;
        this.isEmpty = false;
    }

    public String receiveMessage() {
        String msg = this.msg;
        this.isEmpty = true;
        return msg;
    }
}

class ProducerConsumer {
    public void produce(Message message) {
        String producedStrings[] = { "Humpty Dumpty sat on a wall", "Humpty Dumpty had a great fall.",
                "All the king's horses and all the king's men", "Couldn't put Humpty together again.", "EOM" };
        Random random = new Random();

        for (String producedString : producedStrings) {
            while (!message.isEmpty)
                ;
            synchronized (this) {
                if (message.isEmpty) {
                    System.out.println("Sending message: " + producedString);
                    message.sendMessage(producedString);
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Exception in ProducerConsumer.produce() at wait()");
                        e.printStackTrace();
                    }
                }
            }
            int sleepTime = random.nextInt(2000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Exception in ProducerConsumer.produce() at sleep()");
                e.printStackTrace();
            }

        }

    }

    public void consume(Message message) {
        Random random = new Random();

        while (!message.msg.equals("EOM")) {
            while (message.isEmpty)
                ;
            synchronized (this) {
                if (!message.isEmpty) {
                    String consumedString = message.receiveMessage();
                    System.out.println("Received message: " + consumedString);
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Exception in ProducerConsumer.consume() at wait()");
                        e.printStackTrace();
                    }
                }
            }
            int sleepTime = random.nextInt(2000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Exception in ProducerConsumer.consume() at sleep()");
                e.printStackTrace();
            }
        }
    }
}

class Concurrency_07_ProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Message message = new Message("SOM", true);

        Thread producer = new Thread(new Runnable() {

            @Override
            public void run() {
                producerConsumer.produce(message);
            }
        });

        Thread consumer = new Thread(new Runnable() {

            @Override
            public void run() {
                producerConsumer.consume(message);
            }
        });

        producer.start();
        consumer.start();

        
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            System.out.println("Exception in Concurrency_07_ProducerConsumer");
            e.printStackTrace();
        }
    }
}